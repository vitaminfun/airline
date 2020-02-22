package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateFlight")
public class ServletUpdateFlight extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idFlight"));
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script>\n" +
                "    function back() {\n" +
                "        document.location.replace(\"indexFlightsAdm.html\")\n" +
                "    }\n" +
                "</script>\n" +
                "<h2 align=\"center\">Update flight</h2>\n" +
                "    \n" +
                "<br>\n" +
                "<br>\n" +
                "    <form align=\"center\" id=\"updateFlight\" action='./showUpdatedFlight' method=\"post\">\n" +
                "        <input id=\"idFlight\" type=\"text\" name=\"idFlight\" value=\""+id+"\" hidden>\n" +
                "        <input id = \"idAirplane\" type=\"text\" name=\"idAirplane\" value=\"\" placeholder=\"ID airplane\">\n" +
                "        <input id = \"from\" type=\"text\" name=\"from\" value=\"\" placeholder=\"from\">\n" +
                "        <input id = \"to\" type=\"text\" name=\"to\" value=\"\" placeholder=\"to\">\n" +
                "        <input id = \"date\" type=\"text\" name=\"date\" value=\"\" placeholder=\"YYYY-MM-DD HH-MM-SS\">\n" +
                "        <input id = \"create\" type=\"submit\" name=\"create\" value=\"update flight\">\n" +
                "    </form>\n" +
                "<br>\n" +
                "<p align = \"center\" ><input  type=\"button\" value=\"<< back\"  onclick=\"back()\" ></p>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
