package com.seven.dust.config;

import java.util.Hashtable;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.config
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class Config {
    private final static Hashtable<String, String> config = new Hashtable<>();
    private Config() {}

    private static class InstanceHolder {
        private final static Config instance = new Config();
    }

    public static Config getInstance() {
        return InstanceHolder.instance;
    }
    public static void set(String key, String value) {
        config.put(key, value);
    }
    public static String get(String key) {
        return config.get(key);
    }
}
