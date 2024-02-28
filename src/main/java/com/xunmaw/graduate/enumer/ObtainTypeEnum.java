package com.xunmaw.graduate.enumer;

public enum ObtainTypeEnum {
    SELF("1","自主就业"),
    UNSELF("0","非自主就业");

    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;

    private ObtainTypeEnum(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
