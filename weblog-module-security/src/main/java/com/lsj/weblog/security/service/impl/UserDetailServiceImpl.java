package com.lsj.weblog.security.service.impl;

import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.vo.UserAuth;
import com.lsj.weblog.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo = usersService.findUserVoByUsername(username);
        return new UserAuth(userVo);
    }
}
