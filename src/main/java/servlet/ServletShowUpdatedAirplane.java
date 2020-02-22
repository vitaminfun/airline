package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Airplane;
import com.epam.training.entity.Flight;
import com.epam.training.helperAuth.DateConversion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletShowUpdatedAirplane")
public class ServletShowUpdatedAirplane extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("idAirplane"));
            System.out.println(id);
            String name = request.getParameter("name");
            System.out.println(name);
            int numbersOfSeats = Integer.parseInt(request.getParameter("numbersOfSeats"));
            System.out.println(numbersOfSeats);
            Airplane airplane = new Airplane(id, name, numbersOfSeats);
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            AirplaneDAO airplaneDAO = jdbcDaoFactory.getAirplaneDAO();
            airplaneDAO.update(airplane);

            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</script>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"airplane updated successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexAirplanesAdm.html\");\n"+
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }catch (NullPointerException  | NumberFormatException e ){
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error updating airplane\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexAirplanesAdm.html\");\n"+
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
