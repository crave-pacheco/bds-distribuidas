package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import itesm.mx.dao.AdministrarProgramaDao;

public class BorrarPrograma implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new AdministrarProgramaDao().borrarPrograma(request.getParameter("id"));
        return "programa/programa.jsp";
    }
}
