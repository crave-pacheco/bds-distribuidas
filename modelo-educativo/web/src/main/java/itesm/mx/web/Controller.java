package itesm.mx.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            Class<?> clazz = Class.forName("itesm.mx.web." + action);
//            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//            Action p = (Action) webApplicationContext.getBean(action, clazz);
            Action p = (Action) clazz.newInstance();
            String dest = p.perform(request, response);
//            request.getRequestDispatcher(dest).forward(request, response);
            response.sendRedirect(dest);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            Class<?> clazz = Class.forName("itesm.mx.web." + action);
//            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//            Action p = (Action) webApplicationContext.getBean(action, clazz);
            Action p = (Action) clazz.newInstance();
            String dest = p.perform(request, response);
            request.getRequestDispatcher(dest).forward(request, response);
//            response.sendRedirect(dest);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
