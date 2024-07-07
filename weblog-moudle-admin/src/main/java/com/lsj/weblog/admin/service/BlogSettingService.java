package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.UpdateBlogSettingReqDto;
import com.lsj.weblog.admin.model.vo.BlogSettingVo;

public interface BlogSettingService {

    void updateBlogSetting(UpdateBlogSettingReqDto updateBlogSettingReqDto);

    BlogSettingVo findDetail();
}
