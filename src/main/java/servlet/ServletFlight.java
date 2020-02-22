package servlet;


import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;

import com.epam.training.dao.UserDAO;
import com.epam.training.entity.Flight;
import com.epam.training.entity.User;
import com.epam.training.helperAuth.HelperAuth;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletFlight")
public class ServletFlight extends HttpServlet {
    /**
     * @param request  - coming from pages indexFlights.html,indexFlightsAdm.html to form a table of available flights
     * @param response - list of available flights to form a table
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        DAOFactory JdbcDaoFactory = DAOFactory.getDAOFactory();
        FlightDAO flightDAO = JdbcDaoFactory.getFlightDAO();
        List<Flight> flights = new ArrayList<>();
        flights = flightDAO.findAll();
        String resp = gson.toJson(flights);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
