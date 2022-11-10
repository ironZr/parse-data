package com.zr.parsedata.common.entity;

import lombok.*;

import java.io.Serializable;

@ToString
public class R<T> implements Serializable {

    private static final long serialVersionUID = 123456789L;

    public final static int SUCCESS = 200;

    public final static int FAIL = 500;

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String msg;

    @Setter
    @Getter
    private T data;

    private static <T> R<T> getResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> R<T> ok() {
        return getResult(null, SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return getResult(data, SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return getResult(data, SUCCESS, msg);
    }

    public static <T> R<T> failed() {
        return getResult(null, FAIL, null);
    }

    public static <T> R<T> failed(T data) {
        return getResult(data, FAIL, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return getResult(data, FAIL, msg);
    }


}
