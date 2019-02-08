package com.seven.dust.boot;

import com.seven.dust.config.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.boot
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class EnvInitlization {
    private Class<?> application;
    public EnvInitlization(Class<?> application) {
        this.application = application;
    }
    private Config config = Config.getInstance();
    public void init() throws IOException  {
        this.loadAppProperties();
        this.loadDbProperties();
    }

    /**
     * load app.properties config
     * @throws IOException
     */
    private void loadAppProperties () throws IOException {
        Properties properties = new Properties();

        String appConfigPath = application.getClassLoader().getResource("app.properties").getPath();
        config.set("root", System.getProperty("user.dir"));
        config.set("app.properties", appConfigPath);
        InputStream appStream = new FileInputStream(appConfigPath);
        properties.load(appStream);
        properties.forEach((key, value) -> {
            config.set(key.toString(), value.toString());
        });
    }

    /**
     * load db.properties config
     * @throws IOException
     */
    private void loadDbProperties () throws IOException {
        Properties properties = new Properties();
        String dbConfigPath = this.getClass().getClassLoader().getResource("db.properties").getPath();
        InputStream dbStream = new FileInputStream(dbConfigPath);
        properties.load(dbStream);
        properties.forEach((key, value) -> {
            config.set("db." + key.toString(), value.toString());
        });
    }
}
