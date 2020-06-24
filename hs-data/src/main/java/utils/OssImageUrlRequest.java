package utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: xinhuashe-cms
 * @description: 远程图片地址列表
 * @author: qinxiangyang
 * @create: 2019-07-31 11:00
 **/

@Data
@ApiModel
public class OssImageUrlRequest {
    @ApiModelProperty(value = "远程图片地址列表")
    private List<String> imageUrlList;
}