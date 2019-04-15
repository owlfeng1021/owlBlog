package com.owl.owlBlog.Controller;


import com.owl.owlBlog.exception.TipException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
/*
现在是基本配置
* */
    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        e.printStackTrace();
        return "comm/error_404";
    }
    @ExceptionHandler(value = TipException.class)
    public String TipException(Exception e){
        e.printStackTrace();
        return "comm/error_500";
    }
}
