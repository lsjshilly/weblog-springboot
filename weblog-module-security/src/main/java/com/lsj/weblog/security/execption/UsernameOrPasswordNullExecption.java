package com.lsj.weblog.security.execption;


import org.springframework.security.core.AuthenticationException;

public class UsernameOrPasswordNullExecption extends AuthenticationException {
    public UsernameOrPasswordNullExecption(String msg) {
        super(msg);
    }

    public UsernameOrPasswordNullExecption(String msg, Throwable cause) {
        super(msg, cause);
    }
}
