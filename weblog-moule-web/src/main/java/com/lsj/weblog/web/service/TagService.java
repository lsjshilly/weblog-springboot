package com.lsj.weblog.web.service;


import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.web.domain.dto.AddTagReqDto;
import com.lsj.weblog.web.domain.dto.FindTagPageReqDto;
import com.lsj.weblog.web.domain.dto.SearchTagReqDto;
import com.lsj.weblog.web.domain.vo.TagVo;

import java.util.List;

public interface TagService {

    void addTag(AddTagReqDto addTagReqDto);

    PageResult<TagVo> findTagPage(FindTagPageReqDto findTagPageReqDto);

    void deleteTag(IdRequestDto idRequestDto);

    List<TagVo> searchTags(SearchTagReqDto searchTagReqDto);

}
