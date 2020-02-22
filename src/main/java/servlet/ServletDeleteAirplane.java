package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteAirplane")
public class ServletDeleteAirplane extends HttpServlet {
    /**
     *
     * @param request - id Airplane  for deleting from database from page indexAirplaneAdm.html
     * @param response -  displaying a message about the result of deleting an airplane object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idAirplaneDelete"));
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            AirplaneDAO airplaneDAO = jdbcDaoFactory.getAirplaneDAO();
            airplaneDAO.deleteAirplaneById(id);
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"airplane deleted successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexAirplanesAdm.html\");\n" +
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
                    "    alert(\"error deleting airplane\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexAirplanesAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
