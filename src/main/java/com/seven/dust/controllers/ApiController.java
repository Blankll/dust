package com.seven.dust.controllers;

import com.google.gson.Gson;
import com.seven.dust.responses.RestRep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApiController {
    protected HttpServletRequest req;
    protected HttpServletResponse rep;

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }
    public void setRep(HttpServletResponse rep) {
        this.rep = rep;
    }

    public void initRequest(HttpServletRequest req, HttpServletResponse rep) {
        this.setReq(req);
        this.setRep(rep);
    }

    /**
     * 操作成功返回json格式
     * @param message
     * @throws IOException
     */
    public <T> void success(String message) {
        RestRep<T> data =  this.<T>setData(2000, message);
        this.<T>sendData(data);
        return;
    }
    public <T> void success(String message, T data){
        RestRep<T> one = this.setData(2000, message, data);
        this.<T>sendData(one);
    }
    public <T> void success(T data) {
        RestRep<T> one = this.setData(2000, "请求成功", data);
        this.<T> sendData(one);
    }
    /**
     * 400呀
     */
    public void youFucked() {
        this.rep.setStatus(400);
    }
    /**
     * 404呀 not find
     */
    public void notFind() {
        this.rep.setStatus(404);
    }
    /**
     * 500 呀
     */
    public void iFucked() {
        this.rep.setStatus(500);
    }
    private <T> RestRep<T> setData(int code, String message) {
        RestRep<T> rep = new RestRep<>(code, message, null);

        return rep;
    }
    private <T> RestRep<T> setData(int code, String message, T data){
        RestRep<T> rep = new RestRep<>(code, message, data);
        
        return rep;
    }

    private <T> void sendData(RestRep<T> data){
        Gson gson = new Gson();
        String json = gson.toJson(data);

        this.rep.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
        this.rep.setContentType("application/json;charset=UTF-8");
        PrintWriter out;
        try {
            out = this.rep.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            this.iFucked();
            return;
        }
    }
}