package com.epam.training.dao.jdbc;

import com.epam.training.dao.UserDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.entity.User;
import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDAO extends JdbcGenericDAO<User> implements UserDAO {
    private static final Logger log = LogManager.getLogger(JdbcUserDAO.class);
    public static final String USER_COLUM_ID = "id_user";
    public static final String USER_COLUM_LOGIN = "login";
    public static final String USER_COLUM_FIRST_NAME = "first_name";
    public static final String USER_COLUM_LAST_NAME = "last_name";
    public static final String USER_COLUM_EMAIL = "email";
    public static final String USER_COLUM_PASSWORD = "password";
    public static final String USER_COLUM_ADMIN = "isAdmin";


    @Override
    public User getUserById(int id) {
        List<User> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM user WHERE id_user = ?";
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
    public void deleteUserById(int id) {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM user WHERE id_user = ? ";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException | InterruptedException e) {
            log.warn(e);

        }finally {
            close(connection, statement);
        }

    }

    @Override
    public User getUserByLogin(String login) {
        List<User> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM user WHERE login = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
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
    public User getUserByFirstName(String firstName) {
        List<User> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM user WHERE login = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(connection, statement);
        }
    }

    @Override
    public User getUserByLastName(String lastName) {
        List<User> list;
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM user WHERE last_name = ?";
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, lastName);
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
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("USER_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("USER_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("USER_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("USER_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2,entity.getFirstName());
        statement.setString(3,entity.getLastname());
        statement.setString(4,entity.getEmail());
        statement.setString(5,entity.getPassword());
        statement.setBoolean(6,entity.isAdmin());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastname());
        statement.setString(4, entity.getEmail());
        statement.setString(5, entity.getPassword());
        statement.setBoolean(6, entity.isAdmin());
        statement.setInt(7,entity.getId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User entity) throws SQLException {
        statement.setInt(1,entity.getId());
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while(resultSet.next()){
            try{
                User user = new User();
                user.setId(resultSet.getInt(USER_COLUM_ID));
                user.setLogin(resultSet.getString(USER_COLUM_LOGIN));
                user.setFirstName(resultSet.getString(USER_COLUM_FIRST_NAME));
                user.setLastname(resultSet.getString(USER_COLUM_LAST_NAME));
                user.setEmail(resultSet.getString(USER_COLUM_EMAIL));
                user.setPassword(resultSet.getString(USER_COLUM_PASSWORD));
                user.setAdmin(resultSet.getBoolean(USER_COLUM_ADMIN));
                list.add(user);
            } catch (SQLException e) {
                log.warn(e);
            }
        } return list;
    }


    @Override
    public void read(User entity) {

    }
}
