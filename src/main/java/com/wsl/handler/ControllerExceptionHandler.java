package com.wsl.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice//会拦截所有带有@Controller的请求
public class ControllerExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)//标记这个方法作为异常处理
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL :{},Exception : {},",request.getRequestURI(),e);
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURI());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
