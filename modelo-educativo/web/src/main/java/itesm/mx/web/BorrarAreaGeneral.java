package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import itesm.mx.dao.AdministrarAreaGeneralDao;

public class BorrarAreaGeneral implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new AdministrarAreaGeneralDao().borrarAreaGeneral(request.getParameter("id"));
        return "area-general/area-general.jsp";
    }
}
