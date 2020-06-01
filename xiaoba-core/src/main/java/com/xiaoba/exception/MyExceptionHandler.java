package com.xiaoba.exception;

import com.xiaoba.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public String handleMyException(MyException e, HttpServletRequest request){
        request.setAttribute("javax.servlet.error.status_code",401);
        log.error(e.getMessage(),e);
        return "forward:/error";
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.PATH_NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.DUPLICATE_KEY);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.NO_AUTH);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.exception();
    }
}
