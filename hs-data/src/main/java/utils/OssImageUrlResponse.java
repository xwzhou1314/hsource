package utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: xinhuashe-cms
 * @description: Oss图片URl
 * @author: qinxiangyang
 * @create: 2019-07-31 11:02
 **/
@ApiModel
@Data
public class OssImageUrlResponse {
    @ApiModelProperty(value = "返回的本地图片地址的列表")
    private List<String> ossImageUrlList;

    @ApiModelProperty(value = "成功上传了多少张图片")
    private Integer num;
}