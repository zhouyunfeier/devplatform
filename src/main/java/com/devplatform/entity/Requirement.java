package com.devplatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Requirement {

    private String requirementid;

    private String projectid;

    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
    private Date updateDate;

    private String founder;

    private String requirementtext;

    private String requirementhtml;

    public String getRequirementid() {
        return requirementid;
    }

    public void setRequirementid(String requirementid) {
        this.requirementid = requirementid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getRequirementtext() {
        return requirementtext;
    }

    public void setRequirementtext(String requirementtext) {
        this.requirementtext = requirementtext;
    }

    public String getRequirementhtml() {
        return requirementhtml;
    }

    public void setRequirementhtml(String requirementhtml) {
        this.requirementhtml = requirementhtml;
    }
}
