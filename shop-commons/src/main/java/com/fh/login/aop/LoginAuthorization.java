package com.fh.login.aop;

import com.fh.exception.AuthenticateException;
import com.fh.utils.jwt.JwtUtils;
import com.fh.utils.response.ResponseServer;
import com.fh.utils.response.ServerEnum;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
@Order(3)
public class LoginAuthorization {


    @Around(value="execution(* com.fh.controller.*.*(..))&&@annotation(loginAnnotation)")
    public Object loginAround(ProceedingJoinPoint joinPoint,LoginAnnotation loginAnnotation){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token =request.getHeader("token");
        if(StringUtils.isBlank(token)){
            //自定义异常
            throw new AuthenticateException(ServerEnum.TOKEN_TIMEOUT);
        }
        ResponseServer responseServer = JwtUtils.resolverToken(token);
        if(responseServer.getCode() != 200){
            throw new AuthenticateException(ServerEnum.TOKEN_TIMEOUT);
        }
        Claims claims= (Claims) responseServer.getData();
        String phone= (String) claims.get("phone");
        request.setAttribute("phone",phone);
        Object obj=null;
        try {
            obj=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }



}
