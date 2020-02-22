package servlet;

import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
    /**
     *
     * @param request  - id user for deleting from database from page indexUsersAdm.html
     * @param response - displaying a message about the result of deleting an user object to the database
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idUserDelete"));
            System.out.println(id);
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            UserDAO userDAO = jdbcDaoFactory.getUserDAO();
            userDAO.deleteUserById(id);
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"user deleted successfully\");\n" +
                    "</script>\n" +
                    "<script>\n" +
                    "    document.location.replace(\"indexUsersAdm.html\");\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        }catch (Exception e){
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "</p>\n" +
                    "<script>\n" +
                    "    alert(\"error deleting airplane\");\n" +
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
