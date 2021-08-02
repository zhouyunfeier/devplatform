package com.devplatform.entity;

import java.util.List;

public class PageInfo<T> {
    private List<T> list;
    private int totalPage;
    private int totalCount;
    private int size;
    private int currentPage;

    public PageInfo(){};

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageInfo(List<T> list, int totalPage, int totalCount, int size, int currentPage){
        this.list = list;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.size = size;
        this.currentPage = currentPage;
    }




}
