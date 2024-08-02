package com.lsj.weblog.web.service;


import com.lsj.weblog.web.domain.dto.UpdateBlogSettingReqDto;
import com.lsj.weblog.web.domain.vo.BlogSettingVo;

public interface BlogSettingService {

    void updateBlogSetting(UpdateBlogSettingReqDto updateBlogSettingReqDto);

    BlogSettingVo findDetail();
}
