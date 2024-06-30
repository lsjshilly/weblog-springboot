package com.lsj.weblog.security.service;


import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.dto.UpdateAdminPasswordDto;

public interface UserService {

    UserVo findUserVoByUsername(String username);


    void updatePassword(UpdateAdminPasswordDto updateAdminPasswordDto);

}
