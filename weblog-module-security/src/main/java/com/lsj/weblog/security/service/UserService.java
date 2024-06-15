package com.lsj.weblog.security.service;


import com.lsj.weblog.common.vo.UserVo;

public interface UserService {

    UserVo findUserVoByUsername(String username);


}
