package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.Flight;
import com.epam.training.entity.User;
import com.epam.training.helperAuth.DateConversion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletCreateUser")
public class ServletCreateUser extends HttpServlet {
    /**
     * @param request-values of input fields for the formation of the user object from the page addUser.html
     * @param response       displaying a message about the result of adding an user object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String login = request.getParameter("login");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
            User user = new User(login, firstName, lastName, email, password, admin);
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            UserDAO userDAO = jdbcDaoFactory.getUserDAO();
            userDAO.create(user);
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"User added successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexUsersAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error adding user\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"addUser.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
