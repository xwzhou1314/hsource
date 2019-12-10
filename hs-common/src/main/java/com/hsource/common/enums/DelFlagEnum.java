package com.hsource.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DelFlagEnum {

    DEL_FLAG_TRUE("1", "已删除"),
    DEL_FLAG_FALSE("0", "未删除");

    private String code;
    private String msg;
}
