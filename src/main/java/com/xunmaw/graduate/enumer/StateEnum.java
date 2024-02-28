package com.xunmaw.graduate.enumer;

public enum StateEnum {

    UNCHECKED("0","未审核"),
    PASSED("1","已通过"),
    FAIL("2","未通过");

    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;

    private StateEnum(String code,String msg){
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
