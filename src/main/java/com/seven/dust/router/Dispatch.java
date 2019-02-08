package com.seven.dust.router;

import com.seven.dust.responses.Response;
import com.seven.dust.responses.RestRep;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.router
 * @Date: 1/24/19
 * @Version: 1.0
 */
public class Dispatch extends HttpServlet {
    private Router routers = Router.getInstance();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        this.sevletDispatch(req, resp);
    }

    private void sevletDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI().trim();
        if(uri == "/") {
        }
        RouterItem router = routers.getRoute(uri);

        Method[] methods =  router.getController().getDeclaredMethods();
        Method action = null;
        Method init = null;
        try {
            // 改成直接调用框架的方法,将req 和 resp存储起来,或者时解析请求，然后封装请求的参数进行传递
            init = router.getController().getMethod("initRequest", HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
            this.notFind(resp);
        } catch (SecurityException e1) {
            e1.printStackTrace();
            this.iFucked(resp);
        }

        for(Method method : methods) {
            if(method.getName().toLowerCase().equals(router.getAction().toLowerCase())) {
                action = method;
                break;
            }
        }
        if(null == action) {
            this.notFind(resp);
            return;
        }


        try {
            Object obj = router.getController().getConstructor().newInstance();
            init.invoke(obj, req, resp);
            Class crtype = action.getReturnType();
            RestRep restRep;

            Object result = action.invoke(obj);
            if(crtype.equals(RestRep.class) || crtype.isInstance(RestRep.class)) {
                restRep = (RestRep)result;
            } else {
                restRep = new RestRep(2000, "操作成功", result);
            }
            Response response = new Response(resp);
            response.sendData(200, restRep);

            System.out.println(result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            this.iFucked(resp);
            return;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.youFucked(resp);
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            this.iFucked(resp);
            return;
        } catch (InstantiationException e) {
            e.printStackTrace();
            this.iFucked(resp);
            return;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            this.notFind(resp);
            return;
        } catch (SecurityException e) {
            e.printStackTrace();
            this.iFucked(resp);
            return;
        }

        return;
    }

    /**
     * 400呀
     */
    public void youFucked(HttpServletResponse resp) {
        resp.setStatus(400);
    }
    /**
     * 404呀 not find
     */
    public void notFind(HttpServletResponse resp) {
        resp.setStatus(404);
    }
    /**
     * 500 呀
     */
    public void iFucked(HttpServletResponse resp) {
        resp.setStatus(500);
    }
}
