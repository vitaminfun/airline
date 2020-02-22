package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Airplane;
import com.epam.training.entity.Flight;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletAirplane")
public class ServletAirplane extends HttpServlet {
    /**
     * @param request  - from the page forming the table"indexAdmin.html"
     * @param response -containing a list of all aircraft to form a table on the page "indexAdmin.html"
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        DAOFactory JdbcDaoFactory = DAOFactory.getDAOFactory();
        AirplaneDAO airplaneDAO = JdbcDaoFactory.getAirplaneDAO();
        List<Airplane> airplanes = new ArrayList<>();
        airplanes = airplaneDAO.findAll();
        String resp = gson.toJson(airplanes);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
