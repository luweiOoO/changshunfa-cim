package com.cim.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * oss上传图片返回参数类
 */
@Data
public class AttachmentDto {

    /**
     * oss存储文件夹名称
     */
    private String filePathName;

    /**
     * 存储后返回得图片URL地址
     */
    private String fileUrl;

    /**
     * 存储得图片名称
     */
    private String fileOriginName;

    /**
     * 上传文件得文件类型
     */
    private String extName;

}
