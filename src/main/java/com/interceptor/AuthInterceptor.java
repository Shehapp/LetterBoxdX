package com.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    Logger logger= Logger.getLogger(AuthInterceptor.class.getName());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime=System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        System.out.println("Inside preHandle");
        if(request.getSession().getAttribute("userName")==null){
            response.sendRedirect("/login");
            request.getSession().setAttribute("nextUrl",request.getRequestURI());
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime=System.currentTimeMillis();
        long startTime=(long) request.getAttribute("startTime");
        long time=endTime-startTime;
        logger.info("Time taken by "+request.getRequestURI()+" is "+time+" ms");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
