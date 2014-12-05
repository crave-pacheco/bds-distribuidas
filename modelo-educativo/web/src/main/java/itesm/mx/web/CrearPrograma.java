package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import itesm.mx.dao.AdministrarProgramaDao;
import itesm.mx.model.Programa;

public class CrearPrograma implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Programa programa = new Programa();
        programa.setNombre(request.getParameter("nombrePrograma"));
        programa.setModalidad(request.getParameter("modalidad"));
        programa.setDuracionHoras(Integer.parseInt(request.getParameter("duracionHoras")));
        programa.setDescripcion(request.getParameter("descripcion"));
        programa.setBeneficios(request.getParameter("beneficios"));
        programa.setHorario(request.getParameter("horario"));
        programa.setNombreCampus(request.getParameter("nombreCampus"));
        programa.setAreaGeneralId(UUID.fromString(request.getParameter("areaGeneralId")));
        new AdministrarProgramaDao().crearPrograma(programa);
        return "programa/programa.jsp";
    }
}
