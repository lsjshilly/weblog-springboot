package com.lsj.weblog.common.config;


import com.lsj.weblog.common.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@EnableConfigurationProperties(MinioProperties.class)
@Slf4j
public class MinioAutoConfig {


    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {

        log.info("---------------------开始创建MinioClient-------------------------");

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioProperties.getEndpoint())
                        .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                        .build();

        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
                String policy = String.format("{ \"Statement\": [ { \"Action\": \"s3:GetObject\", \"Effect\": \"Allow\", \"Principal\": \"*\", \"Resource\": \"arn:aws:s3:::%s/*\" } ], \"Version\": \"2012-10-17\" }", minioProperties.getBucketName());
                log.info(policy);
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .config(policy)
                        .build());
            }


        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException("创建MinioClient异常");
        }

        log.info("---------------------结束创建MinioClient-------------------------");
        return minioClient;
    }

}
