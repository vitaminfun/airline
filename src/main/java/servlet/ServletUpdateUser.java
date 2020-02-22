package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateUser")
public class ServletUpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUser"));
        System.out.println(id);

        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script>\n" +
                "    function back() {\n" +
                "        document.location.replace(\"indexUserAdm.html\")\n" +
                "    }\n" +
                "</script>\n" +
                "<h2 align=\"center\">Update user</h2>\n" +
                "\n" +
                "<br>\n" +
                "<br>\n" +
                "<form align=\"center\" id=\"updateUser\" action='./showUpdatedUser'   method=\"post\" accept-charset=\"UTF-8\">\n" +
                "    <input id = \"idUser\" type=\"text\" name=\"idUser\" value=\""+id+"\" hidden>\n" +
                "    <input id = \"login\" type=\"text\" name=\"login\" value=\"\" placeholder=\"login\">\n" +
                "    <input id = \"firstName\" type=\"text\" name=\"firstName\" value=\"\" placeholder=\"first name\">\n" +
                "    <input id = \"lastName\" type=\"text\" name=\"lastName\" value=\"\" placeholder=\"last name\">\n" +
                "    <input id = \"email\" type=\"text\" name=\"email\" value=\"\" placeholder=\"email\">\n" +
                "    <input id = \"password\" type=\"text\" name=\"password\" value=\"\" placeholder=\"password\">\n" +
                "    <input id = \"admin\" type=\"text\" name=\"admin\" value=\"\" placeholder=\"is admin: true/false\">\n" +
                "    <input id = \"update\" type=\"submit\" name=\"update\" value=\"update user\">\n" +
                "</form>\n" +
                "<br>\n" +
                "<p align = \"center\" ><input  type=\"button\" value=\"<< back\"  onclick=\"back()\" ></p>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
