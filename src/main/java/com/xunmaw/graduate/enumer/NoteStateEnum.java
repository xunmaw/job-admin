package com.xunmaw.graduate.enumer;

public class NoteStateEnum {
    public static NoteStateEnum NOINTERVIEWED=new NoteStateEnum("0","未面试");
    public static NoteStateEnum PASSED=new NoteStateEnum("1","已通过");
    public static NoteStateEnum FAIL=new NoteStateEnum("2","未通过");

    private String code;
    private String msg;

    private NoteStateEnum(String code,String msg){
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
