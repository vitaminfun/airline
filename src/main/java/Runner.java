import com.epam.training.dao.*;
import com.epam.training.entity.Airplane;
import com.epam.training.entity.Client;
import com.epam.training.entity.Flight;
import com.epam.training.entity.User;
import com.epam.training.logic.Order;
import com.epam.training.logic.OrderProcessor;

import java.util.*;

/**
 * LowCost Airline System. The Client orders and pays for the Flight Ticket,
 * taking into account the presence / absence of baggage and the right of priority
 * registration and boarding (Ticket price may be lower than the cost of baggage).
 * With the approach of the Flight date or filling the plane,
 * the price of the Ticket may increase.
 */
public class Runner {
    public static void main(String[] args) {
        DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();

        Date date = new GregorianCalendar(2020, Calendar.NOVEMBER, 12, 15,10,00).getTime();
        Order order=new Order("Dima","Popkov","GOMEL","LONDON", true, true,date);
        OrderProcessor orderProcessor=new OrderProcessor();
        orderProcessor.processOrder(order);





    }
}