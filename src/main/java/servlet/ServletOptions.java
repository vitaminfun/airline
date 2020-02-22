package servlet;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.FlightDAO;
import com.epam.training.entity.Flight;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletOptions")
public class ServletOptions extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idFlight"));
        response.getWriter().print("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Document</title>\n" +
                "    <script src=\"priceAjax.js\" defer></script>\n" +
                "    <script src=\"getOptions.js\" defer></script>\n" +
                "    <script src=\"showTicket.js\" defer></script>\n" +
                "</head>\n" +
                "<body>\n" +
                        "<script>\n" +
                        "    function back() {\n" +
                        "        document.location.replace(\"indexFlight.html\")\n" +
                        "    }\n" +
                        "</script>\n" +
                "<h2 align = \"center\">Details</h2>\n" +
                "<p>\n" +
                "    <form id = \"formTicket\" action=\"./showTicket\"enctype=\"text/plaine\" method = \"post\">\n" +
                "            <lable for = \"sub\"> Choose flight details </lable>\n" +
                "            <br>\n" +
                "            <input id=\"idFlight\"type=\"text\" value="+id+" name = \"idFlight\" hidden> \n" +          //hidden field
                "            <br>\n" +
                "            <input id=\"luggage\" type=\"checkbox\" name = \"luggage\"> Will you have luggage\n" +
                "            <br>\n" +
                "            <input id = \"vip\" type=\"checkbox\" name = \"vip\"> VIP status\n" +
                "    </form>\n" +
                "</p>\n" +
                "\n" +
                "\n" +
                "<br>\n" +
                "<div id=\"ResponsePrice\"></div>\n" +

                "<p align = \"center\" ><input  type=\"button\" value=\"Check price\"  onclick=\"getOptions()\" ></p>\n" +
                "<p align = \"center\" ><input  type=\"button\" value=\"Get ticket\"  onclick=\"showTicket()\" ></p>\n" +
                "<p align = \"center\" ><input  type=\"button\" value=\"<< back\"  onclick=\"back()\" ></p>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
