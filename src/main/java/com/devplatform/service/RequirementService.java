package com.devplatform.service;

import com.devplatform.entity.PageInfo;
import com.devplatform.entity.Requirement;
import com.devplatform.lang.Result;

import java.sql.Date;

public interface RequirementService {

    Result getRequirements(String projectid,Integer currentPage);

    PageInfo findAllByPage(Integer currentPage , Integer size , String projectid);

    Result saveRequirement(Requirement requirement);

    Result getInfo(String requirementid);

    Result deleteRequirement(String requirementid,String projectid);

    Result updateTitle(String requirementid, String projectid, String title, String updateDate, Integer currentPage);

    Result updateRequirement(Requirement requirement);
}
