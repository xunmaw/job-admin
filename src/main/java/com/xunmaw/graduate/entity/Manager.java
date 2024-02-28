package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 管理员信息
 */
public class Manager implements Serializable {
    /**
     * 管理员编号
     */
    private String managerId;
    /**
     * 管理员姓名
     */
    private String managerName;
    /**
     * 管理员密码
     */
    private String managerPass;
    /**
     * 管理员联系方式
     */
    private String managerContr;

    public Manager() {
    }

    public Manager(String managerId, String managerName, String managerPass, String managerContr) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerPass = managerPass;
        this.managerContr = managerContr;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPass() {
        return managerPass;
    }

    public void setManagerPass(String managerPass) {
        this.managerPass = managerPass;
    }

    public String getManagerContr() {
        return managerContr;
    }

    public void setManagerContr(String managerContr) {
        this.managerContr = managerContr;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='" + managerId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPass='" + managerPass + '\'' +
                ", managerContr='" + managerContr + '\'' +
                '}';
    }
}
