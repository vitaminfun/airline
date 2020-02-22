package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.DoubleToIntFunction;

@WebServlet(name = "ServletShowUpdatedUser")

public class ServletShowUpdatedUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("idUser"));
            String login = request.getParameter("login");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
            User user = new User(id, login, firstName, lastName, email, password, admin);
            if (login.equals("")||firstName.equals("")||lastName.equals("")||email.equals("")||password.equals("")){
                response.getWriter().println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "</p>\n" +
                        "<script>\n" +
                        "    alert(\"error updating user\");\n" +
                        "</script>\n" +
                        "<script>\n" +
                        "    document.location.replace(\"indexUsersAdm.html\");\n" +
                        "</script>\n" +
                        "</body>\n" +
                        "</html>");
            }else{
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            UserDAO userDAO = jdbcDaoFactory.getUserDAO();
            userDAO.update(user);
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</script>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"user updated successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexUsersAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
            }
        } catch (NullPointerException  | NumberFormatException e) {
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error updating user\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexUsersAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
