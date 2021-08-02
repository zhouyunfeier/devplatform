package com.devplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Milestone {

    private String milestoneid;

    private String projectid;

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

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
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
}
