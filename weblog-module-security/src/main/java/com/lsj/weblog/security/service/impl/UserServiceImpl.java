package com.lsj.weblog.security.service.impl;

import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.entity.User;
import com.lsj.weblog.security.mapper.UserMapper;
import com.lsj.weblog.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public UserVo findUserVoByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}
