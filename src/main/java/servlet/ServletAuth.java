package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.User;
import com.epam.training.helperAuth.HelperAuth;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class ServletAuth extends HttpServlet {
    /**
     * @param user       - user object creation by login and password
     * @param userFromDB - user object received by login with which the external object is compared user
     * @return
     */
    private String checkUser(User user, User userFromDB) {
        if (userFromDB == null) {
            return "The username or password you entered is incorrect";
        } else {
            if (user.getLogin().equals(userFromDB.getLogin()) && user.getPassword().equals(userFromDB.getPassword())) {
                if (userFromDB.isAdmin()) {
                    return "admin";
                } else {
                    return "Successful";
                }
            }
        }
        return "The username or password you entered is incorrect";
    }

    /**
     * @param request  - received object from the registration form
     * @param response - result - the result of comparing the user object with the user object from the database
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
        String jsonUser = reader.readLine();

        User user = gson.fromJson(jsonUser, User.class);

        DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
        UserDAO userDAO = jdbcDaoFactory.getUserDAO();

        User userFromDB = userDAO.getUserByLogin(user.getLogin());

        String result = checkUser(user, userFromDB);
        System.out.println(result);
        HttpSession session = request.getSession();

        if ((result.equals("Successful")) || (result.equals("admin"))) {
            String firstName = userFromDB.getFirstName();
            String lastName = userFromDB.getLastname();
            boolean role = userFromDB.isAdmin();

            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("role", role);

        }
        response.getWriter().print(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
