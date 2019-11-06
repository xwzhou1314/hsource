package com.hsource.upload.service.impl;

import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    private static  final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg", "image/png", "image/bmp");

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            // 检验文件类型
            String contenType = file.getContentType();
            if(! ALLOW_TYPES.contains(contenType)){
                throw new HsException(ExceptionEnum.INVALID_TYPE_ERROR);
            }
            // 检验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(null == image){
                throw new HsException(ExceptionEnum.INVALID_TYPE_ERROR);
            }
            // 1 保存图片
            // 1.1 生成保存目录
            File dir = new File("E:\\upload");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 1.2 保存图片
            file.transferTo(new File(dir, file.getOriginalFilename()));

            // 2.返回路径
            return "http://image.hsource.com/" + file.getOriginalFilename();
        } catch (IOException e) {
            // 上传失败
            log.error("上传失败", e);
            throw new HsException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }

    }
}
