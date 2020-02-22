package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Client;
import com.epam.training.entity.Flight;
import com.epam.training.helperAuth.HelperAuth;
import com.epam.training.helperAuth.HelperReg;
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
import java.util.ArrayList;
import java.util.List;


public class ServletOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        BufferedReader reader = request.getReader();
        String jsonFlight = reader.readLine();
        System.out.println(jsonFlight);
        Flight flight = gson.fromJson(jsonFlight, Flight.class);
        int id = flight.getIdFlight();
        String jsonId = gson.toJson(id);
        response.getWriter().print(jsonId);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
