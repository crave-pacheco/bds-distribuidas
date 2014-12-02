package itesm.mx.dao;

import com.datastax.driver.core.utils.UUIDs;
import itesm.mx.CassandraConnection;
import itesm.mx.model.AreaGeneral;

public class AdministrarAreaGeneralDao {

    public void crearAreaGeneral(AreaGeneral areaGeneral) {
        CassandraConnection._session
            .execute("INSERT INTO programas.area_general (id, nombre) VALUES (?, ?)", UUIDs.timeBased(), areaGeneral.getNombre());
    }
}
