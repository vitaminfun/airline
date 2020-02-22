package com.epam.training.dao.jdbc;

import com.epam.training.dao.GenericDAO;
import com.epam.training.dao.jdbc.connection.ProxyConnection;
import com.epam.training.dao.jdbc.connection.ProxyConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcGenericDAO<T> implements GenericDAO<T> {
    private static final Logger log = LogManager.getLogger(JdbcClientDAO.class);
    final ProxyConnectionPool connectionPool;

    protected JdbcGenericDAO() {
        connectionPool = JdbcDAOFactory.getProxyConnectionPool();
    }


    protected abstract String getSelectQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T entity) throws SQLException;

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public void create(T entity) {
        String sql = getCreateQuery();
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            prepareStatementForCreate(statement, entity);
            if (statement.executeUpdate() != 0) {
                log.info("добавление произошло успешно");
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally{
            close(connection,statement);
        }
    }

    public void update(T entity) {
        String sql = getUpdateQuery();
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, entity);
            if (statement.executeUpdate() != 0) {
                log.info("обновление произошло успешно");
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally{
            close(connection,statement);
        }
    }

    @Override
    public void delete(T entity) {
        String sql = getDeleteQuery();
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            prepareStatementForDelete(statement, entity);
            if (statement.executeUpdate() != 0) {
                log.info("удаление произошло успешно");
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally{
            close(connection,statement);
        }
    }

    public T find(int id) {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += "WHERE id_client=" + id + " ";
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet((resultSet));
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            close(connection, statement);
        }
        if (list.isEmpty() || list.size() == 0) {
            return null;
        }
        return list.iterator().next();

    }

    public List<T> findAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            close(connection, statement);
        }
        return list;
    }

    void close(ProxyConnection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connectionPool.closeConnection(connection);
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
