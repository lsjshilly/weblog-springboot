package com.lsj.weblog.admin.service.impl;

import com.lsj.weblog.admin.mapper.BlogSettingMapper;
import com.lsj.weblog.admin.model.dto.UpdateBlogSettingReqDto;
import com.lsj.weblog.admin.model.entity.BlogSetting;
import com.lsj.weblog.admin.model.vo.BlogSettingVo;
import com.lsj.weblog.admin.service.BlogSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSettingServiceImpl implements BlogSettingService {

    private final static long BLOG_SETTING_ID = 1L;

    private final BlogSettingMapper blogSettingMapper;

    @Override
    public void updateBlogSetting(UpdateBlogSettingReqDto updateBlogSettingReqDto) {

        BlogSetting blogSetting = new BlogSetting();

        BeanUtils.copyProperties(updateBlogSettingReqDto, blogSetting);
        blogSetting.setId(1L);
        blogSettingMapper.updateById(blogSetting);

    }

    @Override
    public BlogSettingVo findDetail() {

        BlogSetting blogSetting = blogSettingMapper.selectById(BLOG_SETTING_ID);
        BlogSettingVo vo = new BlogSettingVo();
        BeanUtils.copyProperties(blogSetting, vo);
        return vo;

    }
}
