package com.xunmaw.graduate.enumer;

public enum ReleaseStateEnum {
    RELEASED("0","未发布"),
    UNRELEASED("1","已发布");

    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;

    private ReleaseStateEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
