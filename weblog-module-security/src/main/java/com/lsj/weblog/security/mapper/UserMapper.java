package com.lsj.weblog.security.mapper;


import com.lsj.weblog.security.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liushijie
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-06-15 17:46:38
 * @Entity generator.domain.User
 */
@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByUsername(String username);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);


}
