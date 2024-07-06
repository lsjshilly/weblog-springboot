package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.AddTagReqDto;
import com.lsj.weblog.admin.model.dto.FindTagPageReqDto;
import com.lsj.weblog.admin.model.dto.SearchTagReqDto;
import com.lsj.weblog.admin.model.vo.TagVo;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;

import java.util.List;

public interface TagService {

    void addTag(AddTagReqDto addTagReqDto);

    PageResult<TagVo> findTagPage(FindTagPageReqDto findTagPageReqDto);

    void deleteTag(IdRequestDto idRequestDto);

    List<TagVo> searchTags(SearchTagReqDto searchTagReqDto);

}
