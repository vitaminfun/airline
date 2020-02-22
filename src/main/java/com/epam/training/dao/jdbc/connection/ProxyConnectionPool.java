package com.epam.training.dao.jdbc.connection;

import com.epam.training.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProxyConnectionPool {
    private static final Logger log = LogManager.getLogger(ProxyConnectionPool.class);
    private ConfigurationManager manager = ConfigurationManager.getInstance();
    private BlockingQueue<ProxyConnection> connectionQueue;

    /**
     * Creates pool of ProxyConnection.
     *
     * @param poolSize when poolSize less than 1 creates pool with one ProxyConnection
     * @throws SQLException will be thrown when ProxyConnection cannot be created
     */
    public ProxyConnectionPool(final int poolSize)  throws SQLException {
        if (poolSize < 1) {
            log.warn("pool size less than 1");
        }
        int n = Math.max(poolSize, 1);
        connectionQueue = new ArrayBlockingQueue<>(n);
        for (int i = 0; i < n; i++) {
            ProxyConnection connection = createConnection();
            connectionQueue.offer(connection);
        }
    }

    /**
     * Gets connection from pool and returns it. If pool has no connections than waits
     * when connection will be returned to pool.
     *
     * @return connection from pool.
     * @throws InterruptedException will be thrown when thread waiting
     *          to get ProxyConnection is interrupted
     */
    public ProxyConnection getConnection() throws InterruptedException {
        return connectionQueue.take();
    }

    /**
     * Returns connection to pool.
     *
     * @param connection connection that needs to be returned to pool
     */
    public void closeConnection(ProxyConnection connection) {
        connectionQueue.offer(connection);
    }

    private ProxyConnection createConnection() throws SQLException {
        try {
            Class.forName(manager.getProperty("DATABASE_DRIVER_NAME"));
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        Connection connection = DriverManager.getConnection(manager.getProperty("DATABASE_URL"),
                manager.getProperty("DATABASE_USER"), manager.getProperty("DATABASE_PASSWORD"));
        return new ProxyConnection(connection);
    }
}
