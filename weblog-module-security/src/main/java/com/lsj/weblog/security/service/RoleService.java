package com.lsj.weblog.security.service;


import com.lsj.weblog.common.vo.RoleVo;

import java.util.List;

public interface RoleService {

    List<RoleVo> findRoleVoByUsername(String username);


}
