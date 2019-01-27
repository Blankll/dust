package com.seven.dust.responses;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: Blank
 * @Description: com.seven.wechat.router
 * @Date: 1/25/19
 * @Version: 1.0
 */
public class Response<T> {
    private HttpServletResponse resp;
    public Response(HttpServletResponse resp) {
        this.resp = resp;
    }
    public  <T>void sendData(int status, RestRep<T> data) throws IOException {
        this.resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write((new Gson().toJson(data)));
        out.close();
    }
}
