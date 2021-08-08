package com.devplatform.entity;

public class Branch {
    //branchid 分支的唯一标识
    private String branchid;

    //分支和项目是多对一的关系，多个分支指向一个项目
    private String projectid;

    //分支名
    private String branchname;

    //分支更新信息
    private String message;

    //分支状态，是否是主分支;
    private int main;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }
}
