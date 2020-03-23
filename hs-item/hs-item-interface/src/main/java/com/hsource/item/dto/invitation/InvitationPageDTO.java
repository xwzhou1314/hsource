package com.hsource.item.dto.invitation;

import com.hsource.common.utils.SplitPageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xwzhou
 * @date 2020-03-23 22:10
 */
@Data
public class InvitationPageDTO extends SplitPageDTO implements Serializable {
    private static final long serialVersionUID = 2945630447233066423L;
    @ApiModelProperty("搜索条件")
    private String title;

    @ApiModelProperty("id")
    private String id;

}
