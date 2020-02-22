package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
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

@WebServlet(name = "ServletPrice")
public class ServletPrice extends HttpServlet {
    /**
     *
     * @param request - from the page getOptions.html
     * @param response - flight details page
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();

        BufferedReader reader = request.getReader();
        String jsonOptions=reader.readLine();
        System.out.println(jsonOptions);
        Options options=gson.fromJson(jsonOptions,Options.class);
        System.out.println(options);


        DAOFactory JdbcDaoFactory=  DAOFactory.getDAOFactory();
        FlightDAO flightDAO=JdbcDaoFactory.getFlightDAO();
        int idFlight = options.getIdFlight();
        Flight flight=flightDAO.getFlightById(idFlight);

        boolean luggage = options.isLuggage();
        boolean vip = options.isVip();

        String firstName = (String) request.getSession().getAttribute("firstName");
        String lastName = (String) request.getSession().getAttribute("lastName");

        Order order=new Order(firstName,lastName,flight.getFrom(),flight.getTo(),vip,luggage,flight.getDate());

        OrderProcessor orderProcessor=new OrderProcessor();
        int price =(int) orderProcessor.calculateCost(order);
        System.out.println(price);

        response.getWriter().print("The price of your ticket "+price);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
