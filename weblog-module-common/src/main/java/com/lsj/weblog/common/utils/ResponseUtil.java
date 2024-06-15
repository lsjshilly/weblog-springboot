package com.lsj.weblog.common.utils;

import com.lsj.weblog.common.base.ResponseResult;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {


    public static void ok(HttpServletRequest request, HttpServletResponse response, ResponseResult<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtil.toJsonStr(result));
        writer.flush();
        writer.close();
    }


    public static void fail(HttpServletRequest request, HttpServletResponse response, ResponseResult<?> result) throws IOException {
        ok(null, response, result);
    }

}
