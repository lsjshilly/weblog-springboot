package com.lsj.weblog.admin.mapper;


import com.github.pagehelper.Page;
import com.lsj.weblog.admin.model.dto.FindCategoryPageReqDto;
import com.lsj.weblog.admin.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author liushijie
 * @description 针对表【tb_category(分类表)】的数据库操作Mapper
 * @createDate 2024-06-30 11:56:52
 * @Entity com.lsj.weblog.admin.model.entity.Category
 */

@Mapper
@Repository
public interface CategoryMapper {

    int deleteById(Long id);

    int insert(Category record);

    Category selectById(Long id);

    Category selectByName(String name);

    int updateById(Category record);

    List<Category> selectList();

    Page<Category> selectPageByCondition(FindCategoryPageReqDto queryCategory);
}
