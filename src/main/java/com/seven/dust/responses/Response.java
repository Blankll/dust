package com.seven.dust.responses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        out.write((gson.toJson(data)));
        out.close();
    }
}
