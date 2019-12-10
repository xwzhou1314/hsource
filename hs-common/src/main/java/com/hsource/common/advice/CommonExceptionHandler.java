package com.hsource.common.advice;

import com.hsource.common.exception.HsException;
import com.hsource.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一的对 controller 的异常拦截
 *
 * @author xwzhou
 * @date 2019/10/11
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HsException.class)
    public ResponseEntity<ExceptionResult>  handleException(HsException em){
        return ResponseEntity.status(em.getExceptionEnum().getCode()).body(new ExceptionResult(em.getExceptionEnum()));
    }
}
