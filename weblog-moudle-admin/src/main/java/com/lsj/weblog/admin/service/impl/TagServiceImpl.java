package com.lsj.weblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsj.weblog.admin.mapper.ArticleTagMapper;
import com.lsj.weblog.admin.mapper.TagMapper;
import com.lsj.weblog.admin.model.dto.AddTagReqDto;
import com.lsj.weblog.admin.model.dto.FindTagPageReqDto;
import com.lsj.weblog.admin.model.dto.SearchTagReqDto;
import com.lsj.weblog.admin.model.entity.Tag;
import com.lsj.weblog.admin.model.vo.TagVo;
import com.lsj.weblog.admin.service.TagService;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.execption.BizExecption;
import com.lsj.weblog.common.execption.ResponseCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.TAG_NAME_EXIST_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {


    private final TagMapper tagMapper;

    private final SqlSessionFactory sqlSessionFactory;

    private final ArticleTagMapper articleTagMapper;


    @Override
    public void addTag(AddTagReqDto addTagReqDto) {

        // 判断是否已存在
        List<Tag> existTags = tagMapper.selectByNames(addTagReqDto.getTags());
        if (existTags != null && !existTags.isEmpty()) {
            log.warn("标签名称已存在：{}", existTags.get(0).getName());
            throw new BizExecption(TAG_NAME_EXIST_ERROR);
        }
        List<Tag> tags = addTagReqDto.getTags().stream().map(tagName -> {
            Tag tag = new Tag();
            tag.setName(tagName.trim());
            return tag;
        }).toList();

    }

    @Override
    public PageResult<TagVo> findTagPage(FindTagPageReqDto findTagPageReqDto) {
        PageHelper.startPage(findTagPageReqDto.getPageNum(), findTagPageReqDto.getPageSize());
        Page<Tag> page = tagMapper.selectPageByCondition(findTagPageReqDto);

        List<TagVo> tagVos = page.getResult().stream().map(tag -> {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(tag, tagVo);
            return tagVo;
        }).toList();
        return PageResult.<TagVo>builder().total(page.getTotal()).items(tagVos).pageSize(page.getPageSize()).currentPage(page.getPageNum()).build();

    }


    @Override
    public void deleteTag(IdRequestDto idRequestDto) {
        if (idRequestDto.getId() == null) {
            throw new BizExecption(ResponseCodeEnum.VALIDATION_ERROR);
        }

        // 判断标签是否在使用
        int count = articleTagMapper.selectCountByTagId(idRequestDto.getId());
        if (count > 0) {
            throw new BizExecption(ResponseCodeEnum.TAG_USED_ERROR);
        }

        int nums = tagMapper.deleteById(idRequestDto.getId());

        if (nums == 0) {
            throw new BizExecption(ResponseCodeEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<TagVo> searchTags(SearchTagReqDto searchTagReqDto) {

        List<Tag> list = tagMapper.selectListByName(searchTagReqDto);

        return list.stream().map(tag -> {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(tag, tagVo);
            return tagVo;
        }).toList();

    }
}
