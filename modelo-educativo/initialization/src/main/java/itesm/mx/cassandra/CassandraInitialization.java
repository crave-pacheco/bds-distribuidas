package itesm.mx.cassandra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.UUID;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.apache.commons.io.IOUtils;

public class CassandraInitialization {

    public static void main(String... args) throws IOException {

        Cluster cluster = null;

        try {

            Properties sitesProperties;
            sitesProperties = new Properties();
            sitesProperties.load(CassandraInitialization.class.getResourceAsStream("sites.properties"));

            String[] sites = ((String) sitesProperties.get("site_ips")).split(",");

            cluster = Cluster.builder().addContactPoints(sites).build();
            Session session = cluster.connect();
            if (cluster.getMetadata().getKeyspace("programas") == null) {
                createKeyspaceAndDbUpdatesTable(session);
            }

            ResultSet resultSet = session.execute("SELECT id, last_script_number FROM programas.db_updates");
            Row dbUpdatesResult = resultSet.one();
            UUID dbUpdatesId = dbUpdatesResult.getUUID("id");
            int lastScriptNumberOnDb = dbUpdatesResult.getInt("last_script_number");
            System.out.println("last script number: " + lastScriptNumberOnDb);
            MigrationScriptsInfo migrationScriptsInfoOnFilesystem = getMigrationScriptsInfoOnFilesystem();

            if (lastScriptNumberOnDb < migrationScriptsInfoOnFilesystem.getLastScriptNumber()) {
                migrationScriptsInfoOnFilesystem.getMissingScriptInfo(lastScriptNumberOnDb).entrySet();
                for (Map.Entry<Integer, String> missingMigrationScript : migrationScriptsInfoOnFilesystem.getMissingScriptInfo(lastScriptNumberOnDb)
                    .entrySet()) {
                    Integer scriptNumber = missingMigrationScript.getKey();
                    String script =
                        IOUtils.toString(CassandraInitialization.class.getResourceAsStream("migration-scripts/" + missingMigrationScript.getValue()));
                    if (script != null && !script.isEmpty()) {
                        session.execute(script);
                    }
                    session.execute(String.format("UPDATE programas.db_updates SET last_script_number = %d WHERE id = %s", scriptNumber, dbUpdatesId));
                }
            }
        } finally {
            if (cluster != null && !cluster.isClosed()) {
                cluster.close();
            }
        }
    }

    private static MigrationScriptsInfo getMigrationScriptsInfoOnFilesystem() throws IOException {
        MigrationScriptsInfo migrationScriptsInfo = new MigrationScriptsInfo();
        InputStream resourceAsStream = CassandraInitialization.class.getResourceAsStream("migration-scripts");
        if (resourceAsStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int scriptNumber = Integer.parseInt(line.replace(".cql", ""));
                migrationScriptsInfo.addScriptInfo(scriptNumber, line);
            }
        }
        return migrationScriptsInfo;
    }

    private static void createKeyspaceAndDbUpdatesTable(Session session) {

        session.execute("CREATE KEYSPACE programas WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};");

        session.execute("CREATE TABLE programas.db_updates (" +
            "id timeuuid PRIMARY KEY," +
            "last_script_number int);");

        session.execute("INSERT INTO programas.db_updates (id, last_script_number) " +
            "VALUES (" +
            "now()," +
            "0);");
    }

    public static class MigrationScriptsInfo {

        private NavigableMap<Integer, String> _orderedScriptNames = new TreeMap<>();

        public int getLastScriptNumber() {
            return _orderedScriptNames.isEmpty() ? 0 : _orderedScriptNames.lastKey();
        }

        public void addScriptInfo(int scriptNumber, String scriptName) {
            _orderedScriptNames.put(scriptNumber, scriptName);
        }

        public NavigableMap<Integer, String> getMissingScriptInfo(int fromScriptNumber) {
            return _orderedScriptNames.tailMap(fromScriptNumber, false);
        }
    }
}
