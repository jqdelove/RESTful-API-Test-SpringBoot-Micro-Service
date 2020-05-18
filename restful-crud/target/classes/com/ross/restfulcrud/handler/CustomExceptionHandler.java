package com.ross.restfulcrud.handler;

import com.ross.restfulcrud.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    //1、浏览器和其他客户端返回的都是json数据
    //处理指定的异常
    /*
    @ExceptionHandler(UserNotExistException.class)
    public @ResponseBody Map<String, String> handleException(Exception e){
        Map<String, String> map = new HashMap<>();
        map.put("code","user.notExist");
        map.put("message",e.getMessage());
        return map;
    }
    */

    //2、根据是浏览器还是客户端自适应返回错误处理结果
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();

        //需要传入对应的状态码，否则默认为200，不会有自适应的效果
        request.setAttribute("javax.servlet.error.status_code","500");

        map.put("code","user.notExist");
        map.put("message",e.getMessage());
        //将错误消息放入request中
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
