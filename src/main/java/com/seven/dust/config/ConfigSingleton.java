package com.seven.dust.config;

import java.util.Hashtable;

/**
 * @Auther: Blank
 * @Description: com.seven.dust.config
 * @Date: 2/9/19
 * @Version: 1.0
 */
public class ConfigSingleton {
    private final static Hashtable<String, String> config = new Hashtable<>();
    private ConfigSingleton() {
        System.out.println("[INFO] user.dir: " + System.getProperty("user.dir"));
    }

    private static class InstanceHolder {
        private final static ConfigSingleton instance = new ConfigSingleton();
    }

    public static ConfigSingleton getInstance() {
        return InstanceHolder.instance;
    }

    public static String get(String key) {
        return config.get(key);
    }
    public static void set(String key, String value) {
        config.put(key, value);
    }
    public Hashtable<String, String> getCOnfigs() {
        return config;
    }

}
