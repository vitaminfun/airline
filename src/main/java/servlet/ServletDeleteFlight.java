package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteFlight")
public class ServletDeleteFlight extends HttpServlet {
    /**
     *
     * @param request  - id Flight for deleting from database from page indexFlightsAdm.html
     * @param response - displaying a message about the result of deleting an flight object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idFlightDelete"));

            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            FlightDAO flightDAO = jdbcDaoFactory.getFlightDAO();
            flightDAO.deleteFlightById(id);

            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"flight deleted successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexFlightsAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }catch (Exception e){
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error deleting flight\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexFlightsAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
