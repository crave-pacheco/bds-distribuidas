package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import itesm.mx.dao.AdministrarProgramaDao;
import itesm.mx.model.Programa;

public class DesplegarProgramas implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Programa> programas = new AdministrarProgramaDao().obtenerProgramas();
        request.setAttribute("programas", programas);
        return "programa/desplegar-programas.jsp";
    }
}
