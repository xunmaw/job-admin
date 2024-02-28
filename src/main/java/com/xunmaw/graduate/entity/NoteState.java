package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 就职状态
 *
 * @author 2023/2/15 11:50
 */
public class NoteState implements Serializable {
    
    private String stateId;
    private String stateName;

    public NoteState() {
    }

    public NoteState(String stateId, String stateName) {
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
        return "NoteState{" +
                "stateId='" + stateId + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
