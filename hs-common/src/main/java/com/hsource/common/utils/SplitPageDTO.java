package com.hsource.common.utils;

import lombok.Data;

import javax.validation.constraints.Max;

/**
 * @author Licoy
 * @version 2018/4/18/14:17
 */
@Data
public abstract class SplitPageDTO {

    private Integer page = 1;

    @Max(value = 100)
    private Integer pageSize = 20;

    private Boolean asc = false;

}
