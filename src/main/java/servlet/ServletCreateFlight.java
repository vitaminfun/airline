package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Flight;
import com.epam.training.helperAuth.DateConversion;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletCreateFlight")
public class ServletCreateFlight extends HttpServlet {
    /**
     *
     * @param request - values of input fields for the formation of the flight object from the page addFlight.html
     * @param response - displaying a message about the result of adding an flight object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int idAirplane = Integer.parseInt(request.getParameter("idAirplane"));
            String from = request.getParameter("from");
            String to = request.getParameter("to");

            String rawDate = request.getParameter("date");
            DateConversion conv= new DateConversion();
            Date date = conv.stringToDate(rawDate);

            Flight flight = new Flight(idAirplane, from, to, date);

            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            FlightDAO flightDAO = jdbcDaoFactory.getFlightDAO();
            flightDAO.create(flight);

            response.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "</p>\n" +
                "<script>\n" +
                "    alert(\"flight added successfully\");\n" +
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
                    "    alert(\"error adding flight\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"addFlight.html\");\n"+
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
