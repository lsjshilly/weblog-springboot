package com.lsj.weblog.security.service.impl;

import com.lsj.weblog.common.execption.BizExecption;
import com.lsj.weblog.common.execption.ResponseCodeEnum;
import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.dto.UpdateAdminPasswordDto;
import com.lsj.weblog.security.domain.entity.User;
import com.lsj.weblog.security.mapper.UserMapper;
import com.lsj.weblog.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserVo findUserVoByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public void updatePassword(UpdateAdminPasswordDto updateAdminPasswordDto) {
        User user = userMapper.selectByUsername(updateAdminPasswordDto.getUsername());
        if (user == null) {
            throw new BizExecption(ResponseCodeEnum.USER_NOT_EXIST_ERROR);
        }

        String encodePassword = passwordEncoder.encode(updateAdminPasswordDto.getPassword());
        if (Objects.equals(encodePassword, user.getPassword())) {
            throw new BizExecption(ResponseCodeEnum.PASSWORD_REPAT_ERROR);
        }
        userMapper.updateUserPassword(updateAdminPasswordDto.getUsername(), encodePassword);
        System.out.println("修改成功");
    }
}
