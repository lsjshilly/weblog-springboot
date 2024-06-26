package com.lsj.weblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsj.weblog.admin.mapper.CategoryMapper;
import com.lsj.weblog.admin.model.dto.AddCategoryDto;
import com.lsj.weblog.admin.model.dto.QueryCategoryDto;
import com.lsj.weblog.admin.model.entity.Category;
import com.lsj.weblog.admin.model.vo.CategoryVo;
import com.lsj.weblog.admin.service.CateGoryService;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.execption.BizExecption;
import com.lsj.weblog.common.execption.ResponseCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.CATEGORY_NAME_EXIST_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class CateGoryServiceImpl implements CateGoryService {


    private final CategoryMapper categoryMapper;


    @Override
    public void addCategory(AddCategoryDto addCategoryDto) {

        // 判断是否已存在
        Category existCategory = categoryMapper.selectByName(addCategoryDto.getName());
        if (existCategory != null) {
            log.warn("分类名称已存在：{}", existCategory.getName());
            throw new BizExecption(CATEGORY_NAME_EXIST_ERROR);
        }

        Category category = new Category();
        category.setName(addCategoryDto.getName().trim());

        categoryMapper.insert(category);
    }

    @Override
    public PageResult<CategoryVo> queryCategoryByCondition(QueryCategoryDto queryCategoryDto) {
        PageHelper.startPage(queryCategoryDto.getPageNum(), queryCategoryDto.getPageSize());
        Page<Category> page = categoryMapper.selectListByCondition(queryCategoryDto);

        List<CategoryVo> categoryVos = page.getResult().stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).toList();
        return PageResult.<CategoryVo>builder().total(page.getTotal()).items(categoryVos).pageSize(page.getPageSize()).currentPage(page.getPageNum()).build();

    }


    @Override
    public void deleteCategory(IdRequestDto idRequestDto) {
        if (idRequestDto.getId() == null) {
            throw new BizExecption(ResponseCodeEnum.VALIDATION_ERROR);
        }

        int nums = categoryMapper.deleteById(idRequestDto.getId());

        if (nums == 0) {
            throw new BizExecption(ResponseCodeEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<CategoryVo> listCategory() {

        List<Category> list = categoryMapper.selectList();

        return list.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).toList();

    }
}
