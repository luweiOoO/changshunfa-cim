package com.cim.common.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.cim.common.config.OssProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * OSS操作公共方法类
 */
@Component
public class OSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    @Resource
    private OssProperties ossProperties;
    @Resource
    private OSS ossClient;

    public OSSUtil() {
    }

    /**
     * 上传文件到OSS
     * @param fileOriginName
     * @param inputStream
     * @return
     */
    public void uploadByOSS(String fileOriginName, InputStream inputStream) throws Exception {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setFileOriginName(fileOriginName);
        attachmentDto.setFilePathName(ossProperties.getFilePath());
        if (!StringUtils.isEmpty(fileOriginName)){
            String extName = fileOriginName.substring(fileOriginName.lastIndexOf(".") + 1).toLowerCase();
            attachmentDto.setExtName(extName);
            if(null == inputStream){
                throw new Exception("inputStream参数不能为空");
//                return StackHttpResponse.error("400","inputStream参数不能为空");
            }else{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String uuid = UUID.randomUUID().toString().toLowerCase();
                String fileName = ossProperties.getFilePath() + "/" + dateFormat.format(new Date()) + "/" + uuid + "." + attachmentDto.getExtName();
                this.ossClient.putObject(this.ossProperties.getBucketName(),fileName,inputStream);
                attachmentDto.setFilePathName(fileName);
                if (this.ossProperties.getPrefix().endsWith("/")){
                    attachmentDto.setFileUrl(this.ossProperties.getPrefix() + fileName);
                } else {
                    attachmentDto.setFileUrl(this.ossProperties.getPrefix() + "/" + fileName);
                }

                logger.info("上传成功：attachmentDto={}",attachmentDto);
//                return StackHttpResponse.success(attachmentDto);
            }
        }else {
            throw new Exception("fileOriginName参数不能为空");
        }
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param fileUrl 被删除文件得完整路径
     */
    public void deleteFile(String fileUrl){
        int position = fileUrl.indexOf(ossProperties.getFilePath());
        String filePath = fileUrl.substring(position);
        logger.info("从OSS删除得文件路径:{}",filePath);
        ossClient.deleteObject(ossProperties.getBucketName(), filePath);
        logger.info("删除" + ossProperties.getBucketName() + "下的文件" + filePath + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public final String getContentType(String fileName){
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
        if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
        if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  ) return "image/jpeg";
        if("png".equalsIgnoreCase(fileExtension)) return "image/png";
        if("html".equalsIgnoreCase(fileExtension)) return "text/html";
        if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
        if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
        if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
        if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";
        if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
        return "text/html";
    }

    /**
     * 销毁
     */
    public void destory(OSSClient client) {
        client.shutdown();
    }
}
