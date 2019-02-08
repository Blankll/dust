package com.seven.dust.database;

import com.seven.dust.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.database
 * @Date: 1/25/19
 * @Version: 1.0
 */
public class DbConnector {

    public static Connection getConnector() {
        Connection connection = null;
        String url = "jdbc:mariadb://" + Config.get("db.host") + ":" + Config.get("db.port") + "/" + Config.get("db.dbname") + "?characterEncoding=utf-8&useSSL" + Config.get("db.ssl");

        try {
            connection = DriverManager.getConnection(url, Config.get("db.username"), Config.get("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("[" + (new Date(System.currentTimeMillis())) + "] get database connetcion failed");
        }

        System.out.println("[" + (new Date(System.currentTimeMillis())) + "] get database connetcion success");

        return connection;
    }
}
