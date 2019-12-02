package com.fh.controller;


import com.fh.exception.AuthenticateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AuthenticateException.class)
    public void authenticateException(AuthenticateException e, HttpServletRequest request, HttpServletResponse response ){
        response.setHeader("NOLOGIN",e.getCode().toString());

    }

    @ExceptionHandler(Exception.class)
    public void execeptionHandler(Exception e, HttpServletResponse response,HttpServletRequest request){

        response.setHeader("EXCEPTION",e.getMessage());

    }


}
