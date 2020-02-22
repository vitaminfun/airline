package com.epam.training.dao.jdbc;


import com.epam.training.dao.AirplaneDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.entity.Airplane;
import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for interaction of objects "airplane" with the database
 */

public class JdbcAirplaneDAO extends JdbcGenericDAO<Airplane> implements AirplaneDAO {
    private static final Logger LOG = LogManager.getLogger(JdbcFlightDAO.class);

    public static final String AIRPLANE_COLUMN_ID = "id_airplane";
    public static final String AIRPLANE_COLUMN_NAME = "name";
    public static final String AIRPLANE_COLUMN_NUMBERSOFSEATS = "numbersOfSeats";

    /**
     * search airplane by id
     *
     * @param id-id airplane
     * @return object airplane
     */
    @Override
    public Airplane getAirplaneById(int id) {
        List<Airplane> list;
        ProxyConnection connection=null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM airplane WHERE airplane.id_airplane = ?";
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
    public void deleteAirplaneById(int id) {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM airplane WHERE id_airplane = ? ";
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
     * search airplane by name airplane
     *
     * @param name-name airplane
     * @return object airplane
     */
    @Override
    public Airplane getAirplaneByName(String name) {
        List<Airplane> list;
        String sql = "SELECT * FROM airplane WHERE name = ? ";
        ProxyConnection connection=null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
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
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("AIRPLANE_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("AIRPLANE_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("AIRPLANE_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("AIRPLANE_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Airplane entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getNumbersOfSeats());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Airplane entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getNumbersOfSeats());
        statement.setInt(3, entity.getId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Airplane entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }

    @Override
    protected List<Airplane> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Airplane> res = new ArrayList<>();
        while (resultSet.next()) {
            try {
                Airplane airplane = new Airplane();
                airplane.setId(resultSet.getInt(AIRPLANE_COLUMN_ID));
                airplane.setName(resultSet.getString(AIRPLANE_COLUMN_NAME));
                airplane.setNumbersOfSeats(resultSet.getInt(AIRPLANE_COLUMN_NUMBERSOFSEATS));
                res.add(airplane);
            } catch (SQLException e) {
                LOG.warn(e);
            }
        }
        return res;
    }

    @Override
    public void read(Airplane entity) {

    }
}
