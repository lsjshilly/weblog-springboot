package com.lsj.weblog.web.mapper;

import com.lsj.weblog.web.domain.entity.BlogSetting;

/**
 * @author liushijie
 * @description 针对表【tb_blog_setting(博客设置信息表)】的数据库操作Mapper
 * @createDate 2024-07-07 21:00:53
 * @Entity com.lsj.weblog.admin.model.entity.BlogSetting
 */
public interface BlogSettingMapper {


    BlogSetting selectById(long id);

    int updateById(BlogSetting record);


}
