package com.xunmaw.graduate.utils;

import java.io.Serializable;

public class SituationJob implements Serializable {
    private double java;
    private double qd;
    private double qt;

    public double getJava() {
        return java;
    }

    public void setJava(double java) {
        this.java = java;
    }

    public double getQd() {
        return qd;
    }

    public void setQd(double qd) {
        this.qd = qd;
    }

    public double getQt() {
        return qt;
    }

    public void setQt(double qt) {
        this.qt = qt;
    }
}
