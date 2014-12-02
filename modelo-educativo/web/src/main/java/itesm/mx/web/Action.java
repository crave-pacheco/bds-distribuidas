package itesm.mx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    String perform(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
