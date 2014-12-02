package itesm.mx;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnection {

    private static Cluster _cluster;
    public static Session _session;

    static {
        _cluster = Cluster.builder().addContactPoints("127.0.0.1").build();
        _session = _cluster.connect();
    }

    public void close() {
        _cluster.close();
    }
}
