package itesm.mx.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.utils.UUIDs;
import itesm.mx.CassandraConnection;
import itesm.mx.model.AreaGeneral;
import itesm.mx.model.Programa;

public class AdministrarProgramaDao {

    public void crearPrograma(Programa programa) {
        CassandraConnection._session
            .execute(String.format("INSERT INTO programas.programa (id, area_general_id, nombre, modalidad, duracion_horas, " +
                "descripcion, beneficios, campus_a_informacion_campus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, {'%s': { horario: '%s' }})", programa.getNombreCampus(), programa.getHorario()), UUIDs.timeBased(),
                programa.getAreaGeneralId(), programa.getNombre(), programa.getModalidad(), programa.getDuracionHoras(), programa.getDescripcion(),
                programa.getBeneficios());
    }

    public void borrarPrograma(String id) {
        CassandraConnection._session.execute("DELETE FROM programas.programa WHERE id = ?", UUID.fromString(id));
    }

    public void modificarPrograma(Programa programa) {
        CassandraConnection._session.execute("UPDATE programas.programa SET nombre = ? WHERE id = ?", programa.getNombre(), programa.getId());
    }

    public List<Programa> obtenerProgramas() {
        List<Programa> programas = new LinkedList<>();
        ResultSet filas = CassandraConnection._session.execute(
            "SELECT id, area_general_id, nombre, modalidad, duracion_horas, descripcion, beneficios, campus_a_informacion_campus FROM programas.programa");
        for (Row fila : filas) {
            Programa programa = new Programa();
            programa.setId(fila.getUUID("id"));
            programa.setAreaGeneralId(fila.getUUID("area_general_id"));
            programa.setNombre(fila.getString("nombre"));
            programa.setModalidad(fila.getString("modalidad"));
            programa.setDuracionHoras(fila.getInt("duracion_horas"));
            programa.setDescripcion(fila.getString("descripcion"));
            programa.setBeneficios(fila.getString("beneficios"));
            Map<String, UDTValue> campusAInformacionCampus = fila.getMap("campus_a_informacion_campus", String.class, UDTValue.class);
            if (!campusAInformacionCampus.isEmpty()) {
                Map.Entry<String, UDTValue> next = campusAInformacionCampus.entrySet().iterator().next();
                programa.setNombreCampus(next.getKey());
                programa.setHorario(next.getValue().getString("horario"));
            }
            programas.add(programa);
        }
        return programas;
    }
}
