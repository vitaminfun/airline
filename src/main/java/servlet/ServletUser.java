package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.Airplane;
import com.epam.training.entity.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletUser")
public class ServletUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        DAOFactory JdbcDaoFactory=  DAOFactory.getDAOFactory();
        UserDAO userDAO =JdbcDaoFactory.getUserDAO();
        List<User> users=new ArrayList<>();
        users=userDAO.findAll();
        String resp = gson.toJson(users);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
