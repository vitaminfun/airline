package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.User;
import com.epam.training.helperAuth.HelperReg;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ServletReg")
public class ServletReg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();

        BufferedReader reader = request.getReader();
        String jsonUser=reader.readLine();

        User user = gson.fromJson(jsonUser, User.class);

        HelperReg helperReg=new HelperReg(user);

        String result=helperReg.addUser();

        response.getWriter().print(result);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
