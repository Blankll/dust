package com.seven.dust.boot;

import com.seven.dust.config.Config;
import com.seven.dust.router.Dispatch;
import com.seven.dust.router.Router;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.boot
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class Server {
    public static void start(Class<?> application) throws Exception{
        // get server instance
        org.eclipse.jetty.server.Server server = Server.serverInit();

        server.start();

        // init router
        Router.init(application);



        System.out.println(server.dump());

        server.join();
    }

    /**
     * init a jetty server and set the init profile
     * @return jetty server instance
     */
    private static org.eclipse.jetty.server.Server serverInit() {
        org.eclipse.jetty.server.Server server =
                new org.eclipse.jetty.server.Server(Integer.parseInt(Config.get("port")));
         // set servlet Context path

        // set servlet handler
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(Dispatch.class,"/*");

        // set resources handler(save file location)
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(".");

        server.setHandler(servletHandler);
//        server.setHandler(resourceHandler);

        return server;

    }
}
