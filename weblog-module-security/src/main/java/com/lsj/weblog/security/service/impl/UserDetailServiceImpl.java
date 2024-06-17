package com.lsj.weblog.security.service.impl;

import com.lsj.weblog.common.vo.RoleVo;
import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.vo.UserAuth;
import com.lsj.weblog.security.service.RoleService;
import com.lsj.weblog.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService usersService;


    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo = usersService.findUserVoByUsername(username);
        List<RoleVo> roleVos = roleService.findRoleVoByUsername(username);
        userVo.setRoleVoList(roleVos);

        return new UserAuth(userVo);
    }
}
