package com.hsource.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadImage(MultipartFile file);
}
