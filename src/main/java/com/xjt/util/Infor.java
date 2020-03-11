package com.xjt.util;

public class Infor {
    private Boolean flag;
    private String msg;
    public Infor(Boolean flag,String msg) {
        // TODO Auto-generated constructor stub
        this.flag = flag;
        this.msg = msg;
    }
    public Boolean getFlag() {
        return flag;
    }
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}