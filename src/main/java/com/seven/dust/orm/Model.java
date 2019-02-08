package com.seven.dust.orm;

import com.seven.dust.database.ConnectorPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: Blank
 * @Description: com.seven.dust.orm
 * @Date: 2/8/19
 * @Version: 1.0
 */
public class Model {
    protected Connection connection;
    protected String table;
    protected String key = "id";


    public Model() {
        this.connection = ConnectorPool.getConnectorPool().getConnection();
    }

    protected Connection getConnection() {
        return this.connection;
    }

    protected Statement getStatement() {
        Statement statement = null;
        try {
            this.connection.createStatement();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return statement;
    }

}
