package com.lsj.weblog.web.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class UserDto {

    @NotNull(message = "用户名不能为空sss")
    private String username;


    private String email;


    private String password;


    private LocalDateTime localDateTime;


    private LocalDate localDate;


    private LocalTime localTime;
}
