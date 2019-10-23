package com.hsource.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常状态码，状态信息
 * @author xwzhou
 * @date 2019-10-11
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400,"价格部位空")
    ;
    private int code;
    private String msg;
}
