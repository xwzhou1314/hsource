package utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: xinhuashe-cms
 * @description:
 * @author: qinxiangyang
 * @create: 2019-07-31 10:08
 **/
@ConfigurationProperties("xinhuamm.oss")
@Component
@Data
public class OssProperties {

    private String endpoint;

    private String accessKeyId;

    private String secretAccessKey;

    private String imgBucket;

    private String videoBucket;

    private String region;

    private String videoHost;

    private String bucketHost;

    private String imgHost;

}