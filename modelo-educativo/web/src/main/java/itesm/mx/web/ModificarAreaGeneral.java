package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import itesm.mx.dao.AdministrarAreaGeneralDao;
import itesm.mx.model.AreaGeneral;

public class ModificarAreaGeneral implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AreaGeneral areaGeneral = new AreaGeneral();
        areaGeneral.setId(UUID.fromString(request.getParameter("id")));
        areaGeneral.setNombre(request.getParameter("nombre"));
        new AdministrarAreaGeneralDao().modificarAreaGeneral(areaGeneral);
        return "area-general/area-general.jsp";
    }
}
