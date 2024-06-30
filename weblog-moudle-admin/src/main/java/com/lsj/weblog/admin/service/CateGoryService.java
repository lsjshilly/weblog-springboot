package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.AddCategoryDto;
import com.lsj.weblog.admin.model.dto.QueryCategoryDto;
import com.lsj.weblog.admin.model.vo.CategoryVo;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;

import java.util.List;

public interface CateGoryService {

    void addCategory(AddCategoryDto addCategoryDto);

    PageResult<CategoryVo> queryCategoryByCondition(QueryCategoryDto queryCategoryDto);

    void deleteCategory(IdRequestDto idRequestDto);

    List<CategoryVo> listCategory();

}
