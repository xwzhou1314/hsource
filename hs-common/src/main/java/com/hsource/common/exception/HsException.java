package com.hsource.common.exception;

import com.hsource.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HsException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
