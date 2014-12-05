package itesm.mx.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import itesm.mx.CassandraConnection;
import itesm.mx.model.AreaGeneral;

public class AdministrarAreaGeneralDao {

    public void crearAreaGeneral(AreaGeneral areaGeneral) {
        CassandraConnection._session
            .execute("INSERT INTO programas.area_general (id, nombre) VALUES (?, ?)", UUIDs.timeBased(), areaGeneral.getNombre());
    }

    public void borrarAreaGeneral(String id) {
        CassandraConnection._session.execute("DELETE FROM programas.area_general WHERE id = ?", UUID.fromString(id));
    }

    public void modificarAreaGeneral(AreaGeneral areaGeneral) {
        CassandraConnection._session.execute("UPDATE programas.area_general SET nombre = ? WHERE id = ?", areaGeneral.getNombre(), areaGeneral.getId());
    }

    public List<AreaGeneral> obtenerAreasGenerales() {
        List<AreaGeneral> areasGenerales = new LinkedList<>();
        ResultSet filas = CassandraConnection._session.execute("SELECT id, nombre FROM programas.area_general");
        for (Row fila : filas) {
            AreaGeneral areaGeneral = new AreaGeneral();
            areaGeneral.setId(fila.getUUID("id"));
            areaGeneral.setNombre(fila.getString("nombre"));
            areasGenerales.add(areaGeneral);
        }
        return areasGenerales;
    }
}
