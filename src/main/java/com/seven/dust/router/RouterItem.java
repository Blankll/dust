package com.seven.dust.router;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.router
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class RouterItem {
    private ReqMethod method;
    private String uri;
    private Class controller;
    private String action;

    public RouterItem(ReqMethod method, String uri, Class controller, String action) {
        this.method = method;
        this.uri = uri;
        this.controller = controller;
        this.action = action;
    }

    public RouterItem(ReqMethod method, String uri, Class controller) {
        this.method = method;
        this.uri = uri;
        this.controller = controller;
        this.action = null;
    }

    public String getUri() {
        return uri;
    }

    public Class getController() {
        return controller;
    }

    public String getAction() {
        return action;
    }
}
