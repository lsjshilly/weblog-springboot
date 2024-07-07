package com.lsj.weblog.common.config;


import com.lsj.weblog.common.execption.BizExecption;
import com.lsj.weblog.common.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.AVATAR_UPLOAD_ERROR;

@Component
@RequiredArgsConstructor
@Slf4j
public class MinioStorageService {

    private final MinioProperties minioProperties;

    private final MinioClient minioClient;


    public String uploadAvatar(String fileName, InputStream inputStream) {
        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(fileName).stream(inputStream, -1, 10485760).build());
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;
        } catch (Exception e) {
            log.error("上传头像失败，文件名：{}", fileName, e);
            throw new BizExecption(AVATAR_UPLOAD_ERROR);
        }
    }


}
