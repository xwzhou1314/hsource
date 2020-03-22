package com.hsource.common.vo;

import lombok.Data;

import java.io.InputStream;

/**
 * @author xwzhou
 * @date 2020-03-18 17:44
 */
@Data
public class FileModel {
    /**
     * 名称
     */
    String fileName;
    /**
     * 类型
     */
    String fileType;
    /**
     * 解压后每个文件的输入流
     */
    InputStream fileInputstream;

}
