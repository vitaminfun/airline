package com.epam.training.dao.jdbc;

import com.epam.training.dao.TicketDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.entity.Ticket;
import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for interaction of objects "Ticket" with the database
 */
public class JdbcTicketDAO extends JdbcGenericDAO<Ticket> implements TicketDAO {
    private static final Logger LOG = LogManager.getLogger(JdbcClientDAO.class);

    public static final String TICKET_COLUMN_ID = "id_ticket";
    public static final String TICKET_COLUMN_ID_FLIGHT = "id_flight";
    public static final String TICKET_COLUMN_SEAT = "seat";
    public static final String TICKET_COLUMN_COST = "cost";

    /**
     * search ticket by id
     *
     * @param id-id ticket
     * @return oblect Ticket
     */
    @Override
    public Ticket getTicketById(int id) {
        List<Ticket> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM ticket WHERE id_ticket = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }

    /**
     * search Ticket by id flightAjax
     *
     * @param idFlight - id flightAjax
     * @return object Ticket
     */
    @Override
    public Ticket getTicketByIdFlight(int idFlight) {
        List<Ticket> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM ticket WHERE id_flight = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idFlight);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }

    /**
     * search Ticket by seat
     *
     * @param seat-ticket seat
     * @return object Ticket
     */
    @Override
    public Ticket getTicketBySeat(int seat) {
        List<Ticket> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM ticket WHERE seat = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, seat);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }


    @Override
    public int createTicket(int idFlight, int seat, double cost) {
        int insertedId=-1;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO ticket (id_flight, seat, cost) VALUES (?, ?, ?)";
        try {
             connection = connectionPool.getConnection();
             statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // SET STATEMENT VARIABLES!!!
            statement.setInt(1, idFlight);
            statement.setInt(2, seat);
            statement.setDouble(3, cost);
            statement.executeUpdate();
            ResultSet resultSet=statement.getGeneratedKeys();
            if (resultSet.next()){
                insertedId=resultSet.getInt(1); //возврат айди тикета для клиента
            }
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return insertedId;
        }finally {
            close(connection, statement);
        }
        return insertedId;
    }

    @Override
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("TICKET_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("TICKET_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("TICKET_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("TICKET_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Ticket entity) throws SQLException {
        statement.setInt(1, entity.getIdFlight());
        statement.setInt(2, entity.getSeat());
        statement.setDouble(3, entity.getCost());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ticket entity) throws SQLException {
        statement.setInt(1, entity.getIdFlight());
        statement.setInt(2, entity.getSeat());
        statement.setDouble(3, entity.getCost());
        statement.setInt(4, entity.getIdTicket());


    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Ticket entity) throws SQLException {
        statement.setInt(1, entity.getIdTicket());

    }

    @Override
    protected List<Ticket> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Ticket> list = new ArrayList<>();
        while (resultSet.next()) {
            try {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(resultSet.getInt(TICKET_COLUMN_ID));
                ticket.setIdFlight(resultSet.getInt(TICKET_COLUMN_ID_FLIGHT));
                ticket.setSeat(resultSet.getInt(TICKET_COLUMN_SEAT));
                ticket.setCost(resultSet.getInt(TICKET_COLUMN_COST));
                list.add(ticket);
            } catch (SQLException ex) {
                LOG.warn(ex);
            }
        }
        return list;
    }

    @Override
    public void read(Ticket entity) {

    }
}
