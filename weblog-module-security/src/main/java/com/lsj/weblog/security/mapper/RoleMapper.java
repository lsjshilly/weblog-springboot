package com.lsj.weblog.security.mapper;


import com.lsj.weblog.security.domain.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liushijie
 * @description 针对表【tb_role】的数据库操作Mapper
 * @createDate 2024-06-17 23:00:23
 * @Entity generator.domain.Role
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectByUserName(@Param("username") String username);
}
