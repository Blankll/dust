package com.seven.dust.security;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.security
 * @Date: 1/25/19
 * @Version: 1.0
 */
public class Password {
    public static String passwordEncrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean passwordCheck(String password, String enPassword) {
        return BCrypt.checkpw(password, enPassword);
    }
}
