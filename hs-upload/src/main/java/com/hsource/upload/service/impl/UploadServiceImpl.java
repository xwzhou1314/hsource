package com.hsource.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.upload.config.UploadProperties;
import com.hsource.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            // 检验文件类型
            String contenType = file.getContentType();
            if (!uploadProperties.getAllowTypes().contains(contenType)) {
                throw new HsException(ExceptionEnum.INVALID_TYPE_ERROR);
            }
            // 检验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (null == image) {
                throw new HsException(ExceptionEnum.INVALID_TYPE_ERROR);
            }

            // 1 保存图片到FastDFS
            String extensionName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extensionName, null);

            // 1 保存图片到本地
//            // 1.1 生成保存目录
//            File dir = new File("F:\\upload", file.getOriginalFilename());
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            // 1.2 保存图片
//            file.transferTo(new File(dir, file.getOriginalFilename()));

            // 2.返回路径
            return uploadProperties.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            // 上传失败
            log.error("上传失败", e);
            throw new HsException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }

    }
}
