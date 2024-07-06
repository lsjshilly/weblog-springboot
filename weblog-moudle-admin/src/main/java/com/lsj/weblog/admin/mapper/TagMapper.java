package com.lsj.weblog.admin.mapper;

import com.github.pagehelper.Page;
import com.lsj.weblog.admin.model.dto.FindTagPageReqDto;
import com.lsj.weblog.admin.model.dto.SearchTagReqDto;
import com.lsj.weblog.admin.model.entity.Tag;

import java.util.List;

/**
 * @author liushijie
 * @description 针对表【tb_tag(标签表)】的数据库操作Mapper
 * @createDate 2024-07-04 22:36:19
 * @Entity com.lsj.weblog.admin.model.entity.Tag
 */
public interface TagMapper {

    int deleteById(Long id);

    int insert(Tag record);

    int insertBatch(List<Tag> records);

    Tag selectById(Long id);

    int updateById(Tag record);


    List<Tag> selectByNames(List<String> names);


    Page<Tag> selectPageByCondition(FindTagPageReqDto findTagPageReqDto);

    List<Tag> selectListByName(SearchTagReqDto searchTagReqDto);
}
