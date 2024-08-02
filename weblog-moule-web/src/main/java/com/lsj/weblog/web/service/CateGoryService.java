package com.lsj.weblog.web.service;

import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.web.domain.dto.AddCategoryReqDto;
import com.lsj.weblog.web.domain.dto.FindCategoryPageReqDto;
import com.lsj.weblog.web.domain.vo.CategoryVo;

import java.util.List;

public interface CateGoryService {

    void addCategory(AddCategoryReqDto addCategoryReqDto);

    PageResult<CategoryVo> findCategoryPage(FindCategoryPageReqDto findCategoryPageReqDto);

    void deleteCategory(IdRequestDto idRequestDto);

    List<CategoryVo> listCategory();

}
