package com.lsj.weblog.web.controller;

import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.config.MinioStorageService;
import com.lsj.weblog.common.execption.BizExecption;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.AVATAR_EMPTY_ERROR;
import static com.lsj.weblog.common.execption.ResponseCodeEnum.AVATAR_UPLOAD_ERROR;

@Slf4j
@RestController
@RequestMapping(value = "/admin/upload")
@RequiredArgsConstructor
@Api(tags = "Admin 文件模块")
public class UploadController {

    private final MinioStorageService minioStorageService;


    @PostMapping(value = "/avatar")
    @ApiOperation(value = "头像上传")
    @ApiOperationLog(description = "头像上传")
    public ResponseResult<String> avatarUpload(@RequestParam(value = "avatar") MultipartFile multipartFile) {

        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.error(AVATAR_EMPTY_ERROR);
        }

        String originalFilename = Objects.requireNonNull(multipartFile.getOriginalFilename());
        log.info("文件名:{}", originalFilename);
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = RandomStringUtils.randomAlphabetic(20) + suffix;
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new BizExecption(AVATAR_UPLOAD_ERROR);
        }
        log.info("新文件名:{}", newFilename);
        String avatarUrl = minioStorageService.uploadAvatar(newFilename, inputStream);

        return ResponseResult.success(avatarUrl);
    }


}
