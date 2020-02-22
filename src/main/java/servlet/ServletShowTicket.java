package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Client;
import com.epam.training.entity.Flight;
import com.epam.training.helperAuth.Options;
import com.epam.training.logic.Order;
import com.epam.training.logic.OrderProcessor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletShowTicket")
public class ServletShowTicket extends HttpServlet {
    /**
     *check the state of the chetbox for ticket pricing
     * @param status - checked/unchecked
     * @return
     */
    private boolean isChecked (String status){
        return "on".equals(status);

    }

    /**
     *
     * @param request - from the page getOptions.html
     * @param response - flight details page
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /*
           getting idflight and chetboxes "luggage" and "VIP" for ticket formation
         */
        int id = Integer.parseInt(request.getParameter("idFlight"));
        String inputLuggage =(request.getParameter("luggage"));
        String inputVip = (request.getParameter("vip"));

        boolean luggage=isChecked(inputLuggage);
        boolean vip =isChecked(inputVip);

        Options options=new Options(id, luggage,vip);
        System.out.println(options);

        DAOFactory JdbcDaoFactory=  DAOFactory.getDAOFactory();
        FlightDAO flightDAO=JdbcDaoFactory.getFlightDAO();
        int idFlight = options.getIdFlight();
        Flight flight=flightDAO.getFlightById(idFlight);

        String firstName = (String) request.getSession().getAttribute("firstName");
        String lastName = (String) request.getSession().getAttribute("lastName");

        Order orderForShow=new Order(firstName,lastName,flight.getFrom(),flight.getTo(), vip, luggage, flight.getDate());
        OrderProcessor processor=new OrderProcessor();
        int price= (int) processor.calculateCost(orderForShow);

        Client currentClient = processor.processOrder(orderForShow);

        Date date = flight.getDate();
        String from = flight.getFrom();
        String to = flight.getTo();
        int idTicket=currentClient.getId_ticket();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script>\n" +
                "    function back() {\n" +
                "        document.location.replace(\"indexFlight.html\")\n" +
                "    }\n" +
                "</script>\n" +
                "<h2 align=\"center\">Information about order</h2>\n" +
                "<p>\n" +
                "    <div> Date "+ date +" </div>\n" +
                "    <div> From "+ from +"</div>\n" +
                "    <div> To "+ to +"</div>\n" +
                "    <div> Price "+ price +"</div>\n" +
                "    <div> ID ticket "+ idTicket +"</div>\n" +
                "\n" +
                "<p align = \"center\" ><input  type=\"button\" value=\"<< back\"  onclick=\"back()\" ></p>\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
