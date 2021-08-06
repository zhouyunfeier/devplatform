package com.devplatform.entity;

import java.sql.Date;


public class Milestone {

    private String milestoneid;

    private String projectname;

    private String projectfounder;

    private String milestonename;

    private Date createDate;

    private Date endDate;

    private String user;

    private Integer percentage;

    private String status;

    private String description;

    public String getMilestoneid() {
        return milestoneid;
    }

    public void setMilestoneid(String milestoneid) {
        this.milestoneid = milestoneid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMilestonename() {
        return milestonename;
    }

    public void setMilestonename(String milestonename) {
        this.milestonename = milestonename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectfounder() {
        return projectfounder;
    }

    public void setProjectfounder(String projectfounder) {
        this.projectfounder = projectfounder;
    }
}
