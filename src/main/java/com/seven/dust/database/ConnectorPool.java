package com.seven.dust.database;

import com.seven.dust.config.Config;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.database
 * @Date: 1/25/19
 * @Version: 1.0
 */
public class ConnectorPool implements DataSource {
    private final static LinkedList<Connection> pool = new LinkedList<>();
    private static int count = 0;

    private ConnectorPool() {
        // load driver
        try {
            Class.forName(Config.get("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("[" + (new Date(System.currentTimeMillis())) + "] database driver not find");
            System.exit(0);
        }
        int poolSize = Integer.parseInt(Config.get("db.connection_pool_minsize"));

        for(int i = 0; i < poolSize; i++) {
            pool.addLast(DbConnector.getConnector());
            System.out.println("database pool create:" + (++count));
        }
    }

    private static class InstanceHolder {
        private final static ConnectorPool instance = new ConnectorPool();
    }

    /**
     * singleton only one instance in application
     * @return
     */
    public static ConnectorPool getConnectorPool() {
        return InstanceHolder.instance;
    }

    @Override
    public synchronized Connection getConnection() {
        if(pool.size() > 0) {
            final Connection connection = pool.removeFirst();
            return (Connection) Proxy.newProxyInstance(ConnectorPool.class.getClassLoader(), connection.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (!method.getName().equals("close")) {
                        return method.invoke(connection, args);
                    } else {
                        // if invoke close function, add connection to connector pool
                        pool.addLast(connection);
                        return null;
                    }
                }
            });
        } else if (count < Integer.parseInt(Config.get("db.connection_pool_maxsize"))) {
            pool.addLast(DbConnector.getConnector());
            return this.getConnection();
        } else {
            // no connector can use now
            throw new RuntimeException("no database connector can use now in pool");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
