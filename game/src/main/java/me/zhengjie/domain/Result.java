package me.zhengjie.domain;

import lombok.Data;

@Data
public class Result {
    private Object data;

    private String msg;

    private Integer code;
}
