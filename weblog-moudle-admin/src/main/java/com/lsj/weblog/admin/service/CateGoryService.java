package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.AddCategoryReqDto;
import com.lsj.weblog.admin.model.dto.FindCategoryPageReqDto;
import com.lsj.weblog.admin.model.vo.CategoryVo;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;

import java.util.List;

public interface CateGoryService {

    void addCategory(AddCategoryReqDto addCategoryReqDto);

    PageResult<CategoryVo> findCategoryPage(FindCategoryPageReqDto findCategoryPageReqDto);

    void deleteCategory(IdRequestDto idRequestDto);

    List<CategoryVo> listCategory();

}
