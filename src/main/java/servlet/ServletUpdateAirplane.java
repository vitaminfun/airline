package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateAirplane")
public class ServletUpdateAirplane extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idAirplane"));
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script>\n" +
                "    function back() {\n" +
                "        document.location.replace(\"indexAirplaneAdm.html\")\n" +
                "    }\n" +
                "</script>\n" +
                "<h2 align=\"center\">Update airplane</h2>\n" +
                "\n" +
                "<br>\n" +
                "<br>\n" +
                "<form align=\"center\" id=\"updateAirplane\" action='./showUpdatedAirplane'  accept-charset=\"UTF-8\" method=\"post\">\n" +
                "    <input id = \"idAirplane\" type=\"text\" name=\"idAirplane\" value=\""+id+"\"hidden>\n" +
                "    <input id = \"name\" type=\"text\" name=\"name\" value=\"\" placeholder=\"name airplane\">\n" +
                "    <input id = \"numbersOfSeats\" type=\"text\" name=\"numbersOfSeats\" value=\"\" placeholder=\"number of seats\">\n" +
                "    <input id = \"create\" type=\"submit\" name=\"create\" value=\"update airplane\">\n" +
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
