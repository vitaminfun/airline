package com.epam.training.dao.jdbc;

import com.epam.training.dao.FlightDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.entity.Flight;

import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dao for interaction of objects "Flight" with the database
 */
public class JdbcFlightDAO extends JdbcGenericDAO<Flight> implements FlightDAO {
    private static final Logger LOG = LogManager.getLogger(JdbcClientDAO.class);

    public static final String FLIGHT_COLUMN_ID = "id_flight";
    public static final String FLIGHT_COLUMN_IDAIRPLANE = "id_airplane";
    public static final String FLIGHT_COLUMN_FROM = "from";
    public static final String FLIGHT_COLUMN_TO = "to";
    public static final String FLIGHT_COLUMN_DATE = "date";

    /**
     * search Flight by id
     *
     * @param id-id flightAjax
     * @return object Flight
     */
    @Override
    public Flight getFlightById(int id) {
        List<Flight> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM flight WHERE id_flight = ? ";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }

            return list.get(0);
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;

        }finally {
            close(connection, statement);
        }

    }

    public void deleteFlightById(int id) {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM flight WHERE id_flight = ? ";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);

        }finally {
            close(connection, statement);
        }

    }

    /**
     * search Flight by date flightAjax
     *
     * @param dateFlight-id flightAjax
     * @return object Flight
     */
    @Override
    public Flight getFlightByDate(Date dateFlight) {
        List<Flight> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM flight WHERE date = ? ";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(dateFlight.getTime()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }

    /**
     * search flightAjax by client order
     *
     * @param from  - start plase
     * @param to    - destination
     * @param date- date flightAjax
     * @return - object flightAjax
     */
    @Override
    public Flight getFlight(String from, String to, Date date) {
        List<Flight> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM flight WHERE flight.from = ? AND flight.to=? AND date=?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setTimestamp(3, new Timestamp(date.getTime()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (SQLException | InterruptedException e) {
            LOG.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }

    }

    @Override
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("FLIGHT_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("FLIGHT_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("FLIGHT_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("FLIGHT_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Flight entity) throws SQLException {

        statement.setInt(1, entity.getIdAirplane());
        statement.setString(2, entity.getFrom());
        statement.setString(3, entity.getTo());
        statement.setTimestamp(4, new Timestamp(entity.getDate().getTime()));

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Flight entity) throws SQLException {
        statement.setInt(1, entity.getIdAirplane());
        statement.setString(2, entity.getFrom());
        statement.setString(3, entity.getTo());
        statement.setTimestamp(4, new Timestamp(entity.getDate().getTime()));
        statement.setInt(5, entity.getIdFlight());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Flight entity) throws SQLException {

        statement.setInt(1, entity.getIdFlight());
    }

    @Override
    protected List<Flight> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Flight> list = new ArrayList<>();
        while (resultSet.next()) {
            try {
                Flight flight = new Flight();
                flight.setIdFlight(resultSet.getInt(FLIGHT_COLUMN_ID));
                flight.setIdAirplane(resultSet.getInt(FLIGHT_COLUMN_IDAIRPLANE));
                flight.setFrom(resultSet.getString(FLIGHT_COLUMN_FROM));
                flight.setTo(resultSet.getString(FLIGHT_COLUMN_TO));
                Timestamp ts = resultSet.getTimestamp(FLIGHT_COLUMN_DATE);
                flight.setDate(new Date(ts.getTime()));
                list.add(flight);
            } catch (SQLException ex) {
                LOG.warn(ex);
            }
        }
        return list;
    }

    @Override
    public void read(Flight entity) {

    }
}
