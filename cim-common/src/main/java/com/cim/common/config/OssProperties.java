package com.cim.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oss")
@Data
public class OssProperties {

    /**
     * oss服务器地址
     */
    private String endpoint;
    /**
     * oss ID
     */
    private String accessKeyId;
    /**
     * oss 密码
     */
    private String accessKeySecret;
    /**
     * bucketName
     */
    private String bucketName;

    /**
     * 图片存储地址
     */
    private String filePath;

    private String prefix;

    @Bean
    public OSS ossClient() {
        OSS ossClient = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);
        System.out.println("OSS Client created successfully!");
        System.out.println("==========================================");
        return ossClient;
    }

}
