package com.lsj.weblog.admin.enums;

import lombok.Getter;

@Getter
public enum ArticlePublishStatus {

    DRAFIT(0, "草稿"),
    PUBLISH(1, "已发布");


    private final int code;
    
    private final String desc;


    ArticlePublishStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
