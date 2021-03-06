package com.seven.dust.boot;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.boot
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class BootStrap {
    public void run(Class<?> application) throws Exception {
        // load config
        EnvInitlization initEnv = new EnvInitlization(application);
        initEnv.init();
        // start jetty server
        Server.start(application);
    }
}
