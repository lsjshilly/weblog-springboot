package com.lsj.weblog.security.service.impl;

import com.lsj.weblog.common.vo.RoleVo;
import com.lsj.weblog.security.domain.entity.Role;
import com.lsj.weblog.security.mapper.RoleMapper;
import com.lsj.weblog.security.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<RoleVo> findRoleVoByUsername(String username) {

        List<Role> roles = roleMapper.selectByUserName(username);
        ArrayList<RoleVo> roleVos = new ArrayList<>();
        roles.forEach(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role, roleVo);
            roleVos.add(roleVo);
        });


        return roleVos;
    }
}
