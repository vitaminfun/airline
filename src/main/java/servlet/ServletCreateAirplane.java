package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.entity.Airplane;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCreateAirplane")
public class ServletCreateAirplane extends HttpServlet {
    /**
     * @param request  - values of input fields for the formation of the airplane object from the page addAirplane.html
     * @param response - displaying a message about the result of adding an airplane object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String name = request.getParameter("name");
            int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
            Airplane airplane = new Airplane(name, numberOfSeats);

            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            AirplaneDAO airplaneDAO = jdbcDaoFactory.getAirplaneDAO();
            airplaneDAO.create(airplane);
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"airplane added successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexAirplanesAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (NumberFormatException | NullPointerException e) {
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error adding airplane\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"addAirplane.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
