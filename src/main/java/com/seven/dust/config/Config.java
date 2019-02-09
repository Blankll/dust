package com.seven.dust.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.config
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class Config {
    private static final ConfigSingleton  instance = ConfigSingleton.getInstance();

    public static void set(String key, String value) {
        instance.set(key, value);
    }
    public static String get(String key) {
        String result = instance.get(key);
        if(null == instance.get(key)) {
            loadAppProperties();
            loadDbProperties();
        }
        Hashtable<String, String> config = instance.getCOnfigs();
        result = instance.get(key);
        return instance.get(key);
    }

    /**
     * load db.properties config
     * @throws IOException
     */
    private static void loadDbProperties () {
        System.out.println("[INFO] start laod  db.properties");
        Properties properties = new Properties();
        String dbConfigPath = System.getProperty("user.dir") + "/target/classes/db.properties";
        InputStream dbStream = null;
        try {
            dbStream = new FileInputStream(dbConfigPath);
            properties.load(dbStream);
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] file db.properties not find");
            e.printStackTrace();
            System.exit(0);;
        } catch (IOException e) {
            System.err.println("[ERROR] load db.properties failed");
//            e.printStackTrace();
            System.exit(0);;
        }

        properties.forEach((key, value) -> {
            set("db." + key.toString(), value.toString());
        });
    }

    /**
     * load app.properties config
     * @throws IOException
     */
    private static void loadAppProperties (){
        System.out.println("[INFO] start laod  app.properties");
        Properties properties = new Properties();

//        String appConfigPath = Config.class.getClass().getResource("app.properties").getPath();
        String appConfigPath = System.getProperty("user.dir") + "/target/classes/app.properties";
        InputStream appStream = null;
        try {
            appStream = new FileInputStream(appConfigPath);
            properties.load(appStream);
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] file app.properties not find");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.err.println("[ERROR] load app.properties failed");
//            e.printStackTrace();
            System.exit(0);
        }

        properties.forEach((key, value) -> {
            set(key.toString(), value.toString());
        });
    }
}
