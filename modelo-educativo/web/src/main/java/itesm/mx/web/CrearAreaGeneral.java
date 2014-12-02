package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import itesm.mx.dao.AdministrarAreaGeneralDao;
import itesm.mx.model.AreaGeneral;

public class CrearAreaGeneral implements Action {

    public CrearAreaGeneral() {
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AreaGeneral areaGeneral = new AreaGeneral();
        areaGeneral.setNombre(request.getParameter("nombre"));
        new AdministrarAreaGeneralDao().crearAreaGeneral(areaGeneral);
        return "area-general/area-general.jsp";
    }
}
