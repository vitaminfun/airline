package com.epam.training.dao.jdbc;

import com.epam.training.dao.ClientDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.entity.Client;

import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao for interaction of objects "Client" with the database
 */
public class JdbcClientDAO extends JdbcGenericDAO<Client> implements ClientDAO {
    private static final Logger log = LogManager.getLogger(JdbcClientDAO.class);

    public static final String CLIENT_COLUM_ID = "id_client";
    public static final String CLIENT_COLUM_FIRST_NAME = "first_name";
    public static final String CLIENT_COLUM_LAST_NAME = "last_name";
    public static final String CLIENT_COLUM_ID_TICKET = "id_ticket";
    public static final String CLIENT_COLUM_VIP = "vip";
    public static final String CLIENT_COLUM_LUGGAGE = "luggage";

    @Override
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("CLIENT_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("CLIENT_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("CLIENT_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("CLIENT_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Client entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3, entity.getId_ticket());
        statement.setBoolean(4, entity.isVip());
        statement.setBoolean(5, entity.isLugagge());
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Client entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3, entity.getId_ticket());
        statement.setBoolean(4, entity.isVip());
        statement.setBoolean(5, entity.isLugagge());
        statement.setInt(6, entity.getId());

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Client entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }

    @Override
    protected List<Client> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Client> res = new ArrayList<>();
        while (resultSet.next()) {
            try {
                Client client = new Client();
                client.setId(resultSet.getInt(CLIENT_COLUM_ID));
                client.setFirstName(resultSet.getString(CLIENT_COLUM_FIRST_NAME));
                client.setLastName(resultSet.getString(CLIENT_COLUM_LAST_NAME));
                client.setId_ticket(resultSet.getInt(CLIENT_COLUM_ID_TICKET));
                client.setVip(resultSet.getBoolean(CLIENT_COLUM_VIP));
                client.setLugagge(resultSet.getBoolean(CLIENT_COLUM_LUGGAGE));
                res.add(client);
            } catch (SQLException e) {
                log.warn(e);
            }
        }
        return res;
    }

    @Override
    public Client getClientByName(String firstName, String lastName) {
        List<Client> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM client WHERE first_name = ? AND last_name = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (SQLException | InterruptedException e) {
            log.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }

    /**
     * search Client by id
     *
     * @param id-id client
     * @return object client
     */
    @Override
    public Client getClientById(int id) {
        List<Client> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM client WHERE id_client = ?";
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
            log.warn(e);
            return null;
        }finally {
            close(connection, statement);
        }
    }

    @Override
    public void read(Client entity) {

    }
}
