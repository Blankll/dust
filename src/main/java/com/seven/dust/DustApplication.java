package com.seven.dust;

import com.seven.dust.boot.BootStrap;

/**
 * @Auther: Blank
 * @Description: com.seven.boot
 * @Date: 1/27/19
 * @Version: 1.0
 */
public class DustApplication {
    public static void run(Class<?> application) throws Exception {
        (new BootStrap()).run(application);
    }
}
