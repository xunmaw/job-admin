package com.xunmaw.graduate.utils;

import java.util.List;


/**
 * 分页对象
 * @author Administrator
 *
 * @param <T>
 */
public class Pager<T> {
    /**
     * 分页的大小
     */
    private Integer size;
    /**
     * 当前页码
     */
    private Integer offset;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 分页的数据
     */
    private List<T> datas;

    public Pager() {
    }

    public Pager(Integer size, Integer offset, Integer totalCount, long totalPage, List<T> datas) {
        this.size = size;
        this.offset = offset;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.datas = datas;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}
