package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Flight;
import com.epam.training.helperAuth.DateConversion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletShowUpdatedFlight")
public class ServletShowUpdatedFlight extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("idFlight"));
            int idAirplane = Integer.parseInt(request.getParameter("idAirplane"));
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String rawDate = request.getParameter("date");
            DateConversion conv = new DateConversion();
            Date date = conv.stringToDate(rawDate);

            Flight flight = new Flight(id, idAirplane, from, to, date);

            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            FlightDAO flightDAO = jdbcDaoFactory.getFlightDAO();
            flightDAO.update(flight);

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
                    "    alert(\"flight updated successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexFlightsAdm.html\");\n"+
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
                    "    alert(\"error updating flight\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexFlightsAdm.html\");\n"+
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
