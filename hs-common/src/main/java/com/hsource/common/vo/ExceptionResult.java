package com.hsource.common.vo;

import com.hsource.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * 统一的对 controller 的异常拦截返回的结果
 *
 * @author xwzhou
 * @date 2019/10/11
 */
@Data
public class ExceptionResult {

    private int code;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.code = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
