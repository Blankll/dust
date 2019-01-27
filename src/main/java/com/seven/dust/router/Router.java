package com.seven.dust.router;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.router
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class Router {
    private final static Hashtable<String, RouterItem> routers = new Hashtable<>();
    private Router() {
    }

    public static void init(Class<?> application) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        String routerClass = application.getPackageName() + ".router.ApiRouter";

        DustRouter router = (DustRouter) Class.forName(routerClass).getConstructor().newInstance();
        router.routes();
    }
    private static class InstanceHolder {
        private final static Router instance = new Router();
    }

    public static Router getInstance() {
        return InstanceHolder.instance;
    }

    public static RouterItem getRoute(String uri) {
        return routers.get(uri);
    }

    // get
    public static void GET(String uri, Class controller, String action) {
        addRouter(ReqMethod.GET,uri,controller, action);
    }
    public static void GET(String uri, Class controller) {
        addRouter(ReqMethod.GET,uri,controller, "index");
    }
    // post
    public static void POST(String uri, Class controller, String action) {
        addRouter(ReqMethod.POST,uri,controller, action);
    }
    public static void POST(String uri, Class controller) {
        addRouter(ReqMethod.POST,uri,controller, "index");
    }
    // head
    public static void HEAD(String uri, Class controller, String action) {
        addRouter(ReqMethod.HEAD,uri,controller, action);
    }
    public static void HEAD(String uri, Class controller) {
        addRouter(ReqMethod.HEAD,uri,controller, "index");
    }
    // put
    public static void PUT(String uri, Class controller, String action) {
        addRouter(ReqMethod.PUT,uri,controller, action);
    }
    public static void PUT(String uri, Class controller) {
        addRouter(ReqMethod.PUT,uri,controller, "index");
    }
    // delete
    public static void DELETE(String uri, Class controller, String action) {
        addRouter(ReqMethod.DELETE,uri,controller, action);
    }
    public static void DELETE(String uri, Class controller) {
        addRouter(ReqMethod.DELETE,uri,controller, "index");
    }
    // connect
    public static void CONNECT(String uri, Class controller, String action) {
        addRouter(ReqMethod.CONNECT,uri,controller, action);
    }
    public static void CONNECT(String uri, Class controller) {
        addRouter(ReqMethod.CONNECT,uri,controller, "index");
    }
    // options
    public static void OPTIONS(String uri, Class controller, String action) {
        addRouter(ReqMethod.OPTIONS,uri,controller, action);
    }
    public static void OPTIONS(String uri, Class controller) {
        addRouter(ReqMethod.OPTIONS,uri,controller, "index");
    }
    // trace
    public static void TRACE(String uri, Class controller, String action) {
        addRouter(ReqMethod.TRACE,uri,controller, action);
    }
    public static void TRACE(String uri, Class controller) {
        addRouter(ReqMethod.TRACE,uri,controller, "index");
    }

    private static void addRouter(ReqMethod method, String uri, Class controller, String action) {
        RouterItem item = new RouterItem(method, uri, controller, action);
        routers.put(uri, item);
    }
}
