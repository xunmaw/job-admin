package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 项目经历信息
 */
public class Item implements Serializable {
    /**
     * 项目编码
     */
    private Integer itemId;
    /**
     * 项目名称
     */
    private String itemName;
    /**
     * 项目内容
     */
    private String itemContent;
    /**
     * 项目时间
     */
    private String itemTime;
    /**
     * 求职意向编号
     */
    private String intentionId;
    private Intention intention;

    public Item() {
    }

    public Item(Integer itemId, String itemName, String itemContent, String itemTime, String intentionId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.itemTime = itemTime;
        this.intentionId = intentionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public String getIntentionId() {
        return intentionId;
    }

    public void setIntentionId(String intentionId) {
        this.intentionId = intentionId;
    }

    public Intention getIntention() {
        return intention;
    }

    public void setIntention(Intention intention) {
        this.intention = intention;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemContent='" + itemContent + '\'' +
                ", itemTime='" + itemTime + '\'' +
                ", intentionId='" + intentionId + '\'' +
                ", intention=" + intention +
                '}';
    }
}
