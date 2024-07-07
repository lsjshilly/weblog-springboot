package com.lsj.weblog.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "weblog.minio")
public class MinioProperties {

    private String accessKey;

    private String secretKey;

    private String endpoint;

    private String bucketName;

}
