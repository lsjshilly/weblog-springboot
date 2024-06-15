package com.lsj.weblog.web.mapper;


import com.lsj.weblog.web.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liushijie
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-06-15 13:53:11
 * @Entity generator.domain.User
 */

@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserDto record);

    int insertSelective(UserDto record);

    UserDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDto record);

    int updateByPrimaryKey(UserDto record);

}
