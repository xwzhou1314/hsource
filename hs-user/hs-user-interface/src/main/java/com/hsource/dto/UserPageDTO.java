package com.hsource.dto;

import com.hsource.common.utils.SplitPageDTO;
import lombok.Data;

/**
 * @author xwzhou
 * @date 2020-03-08 16:55
 */
@Data
public class UserPageDTO extends SplitPageDTO {
    private String search;
    private String id;
}
