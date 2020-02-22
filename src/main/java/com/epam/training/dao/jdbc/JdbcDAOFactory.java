package com.epam.training.dao.jdbc;

import com.epam.training.dao.*;
import com.epam.training.dao.jdbc.connection.ProxyConnectionPool;
import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class JdbcDAOFactory extends DAOFactory {
    private static final Logger log = LogManager.getLogger(JdbcDAOFactory.class);
    private static JdbcDAOFactory instance;

    private static ProxyConnectionPool proxyConnectionPool;
    private static ConfigurationManager manager=ConfigurationManager.getInstance();

    private JdbcDAOFactory() {

    }

    public static synchronized JdbcDAOFactory getFactory() {
        if (instance == null) instance = new JdbcDAOFactory();
        return instance;
    }

    static ProxyConnectionPool getProxyConnectionPool() {
        if(proxyConnectionPool==null) {
            int poolSize=Integer.parseInt(manager.getProperty("DATABASE_CONNECTION_POOL_SIZE"));
            try{
                proxyConnectionPool = new ProxyConnectionPool(poolSize);
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return proxyConnectionPool;
    }

    @Override
    public ClientDAO getClientDAO() {
        return new JdbcClientDAO();
    }

    @Override
    public FlightDAO getFlightDAO() {
        return new JdbcFlightDAO();
    }

    @Override
    public AirplaneDAO getAirplaneDAO() {
        return new JdbcAirplaneDAO();
    }

    public TicketDAO getTicketDAO() {
        return new JdbcTicketDAO();
    }
    public UserDAO getUserDAO() {
        return new JdbcUserDAO();
    }

}
