package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import itesm.mx.dao.AdministrarAreaGeneralDao;
import itesm.mx.model.AreaGeneral;

public class CrearProgramaForma implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<AreaGeneral> areasGenerales = new AdministrarAreaGeneralDao().obtenerAreasGenerales();
        request.setAttribute("areasGenerales", areasGenerales);
        return "programa/crear-programa.jsp";
    }
}
