package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 求职状态
 */
public class ObtainState implements Serializable {
    private String stateId;
    private String stateName;

    public ObtainState() {
    }

    public ObtainState(String stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "ObtainState{" +
                "stateId='" + stateId + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
