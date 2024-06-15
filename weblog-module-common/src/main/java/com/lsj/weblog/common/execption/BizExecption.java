package com.lsj.weblog.common.execption;

import lombok.Getter;

@Getter
public class BizExecption extends RuntimeException {


    private final String code;
    private final String message;

    public BizExecption(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizExecption(BaseExecptionInterface baseExecptionInterface) {
        super(baseExecptionInterface.getMessage());
        this.code = baseExecptionInterface.getCode();
        this.message = baseExecptionInterface.getMessage();
    }


}
