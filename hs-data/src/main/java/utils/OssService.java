package utils;

import com.aliyun.oss.OSSClient;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: xinhuashe-cms
 * @description:
 * @author: qinxiangyang
 * @create: 2019-07-31 10:09
 **/
@Service
@Slf4j
public class OssService {
    @Autowired
    private OSSClient ossClient;

    @Autowired
    private OssProperties ossProperties;

    private ThreadLocalRandom random = ThreadLocalRandom.current();


    public OssImageUrlResponse getOssImageUrlRes(OssImageUrlRequest ossImageUrlRequest) {
        int num = ossImageUrlRequest.getImageUrlList().size();
        OssImageUrlResponse ossImageUrlResponse = new OssImageUrlResponse();
        List<String> localImageUrlList = new ArrayList<>();
        for (String imageUrl : ossImageUrlRequest.getImageUrlList()) {
            try {
                byte[] imageBytes = ImageUtil.downloadImage(imageUrl);
                // 获取图片格式
                ImageInputStream imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
                Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReaders(imageInputStream);
                //如果获取图片失败，重新获取一次，再次失败则直接返回给前端原地址
                while (!imageReaderIterator.hasNext()) {
                    imageBytes = ImageUtil.downloadImage(imageUrl);
                    // 获取图片格式
                    imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
                    imageReaderIterator = ImageIO.getImageReaders(imageInputStream);
                    break;
                }
                String fileExtension;
                if (!imageReaderIterator.hasNext()) {
                    fileExtension = ImageUtil.readType(imageBytes);
                } else {
                    ImageReader imageReader = imageReaderIterator.next();
                    fileExtension = imageReader.getFormatName();
                }

                String fileTimestamp = TimeUtil.ossFormat(new Date()) + random.nextInt(10);
                String fileName = Joiner.on(".").join(fileTimestamp, fileExtension);

                // 上传图片到oss
                Calendar now = Calendar.getInstance();
                String filePath = Joiner.on("/").join(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1,
                        now.get(Calendar.DATE), fileName);
                ossClient.putObject(ossProperties.getImgBucket(), filePath, new ByteArrayInputStream(imageBytes));

                String localImageUrl = ossProperties.getImgHost() + filePath;
                localImageUrlList.add(localImageUrl);
            } catch (IOException e) {
                localImageUrlList.add(imageUrl);
                num--;
            }

        }
        ossImageUrlResponse.setOssImageUrlList(localImageUrlList);
        ossImageUrlResponse.setNum(num);
        return ossImageUrlResponse;
    }

    /**
     * 上传远程图片，同时指定上传至OSS的路径
     *
     * @param imgUrl
     * @param key
     * @return
     */
    public String getOssImageUrlRes(String imgUrl, String key) {
        if (StringUtils.isEmpty(imgUrl)) {
            return null;
        }

        try {
            byte[] imageBytes = ImageUtil.downloadImage(imgUrl);
            // 获取图片格式
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
            Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReaders(imageInputStream);
            //如果获取图片失败，重新获取一次，再次失败则直接返回给前端原地址
            while (!imageReaderIterator.hasNext()) {
                imageBytes = ImageUtil.downloadImage(imgUrl);
                // 获取图片格式
                imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
                imageReaderIterator = ImageIO.getImageReaders(imageInputStream);
                break;
            }
            String fileExtension;
            if (!imageReaderIterator.hasNext()) {
                fileExtension = ImageUtil.readType(imageBytes);
            } else {
                ImageReader imageReader = imageReaderIterator.next();
                fileExtension = imageReader.getFormatName();
            }

            String fileTimestamp = TimeUtil.ossFormat(new Date()) + random.nextInt(10);
            String fileName = Joiner.on(".").join(fileTimestamp, fileExtension);

            // 上传图片到oss
            Calendar now = Calendar.getInstance();
            String filePath = key;
            if (StringUtils.isEmpty(filePath)) {
                filePath = Joiner.on("/").join(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1,
                        now.get(Calendar.DATE), fileName);
            }
            ossClient.putObject(ossProperties.getImgBucket(), filePath, new ByteArrayInputStream(imageBytes));

            return ossProperties.getImgHost() + filePath;
        } catch (IOException e) {
            log.error("图片上传失败", e);
            return null;
        }
    }

    public String getFileUrlRes(File file) {

        try {

            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String fileTimestamp = TimeUtil.ossFormat(new Date()) + random.nextInt(10);
            fileName = Joiner.on(".").join(fileTimestamp, suffix);

            // 上传图片到oss
            Calendar now = Calendar.getInstance();
            String filePath = Joiner.on("/").join(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1,
                    now.get(Calendar.DATE), fileName);
            ossClient.putObject(ossProperties.getImgBucket(), filePath, new FileInputStream(file));

            String ossUrl = ossProperties.getImgHost() + filePath;
            return ossUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}