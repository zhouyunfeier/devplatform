package com.devplatform.service;

import com.devplatform.entity.PageInfo;
import com.devplatform.entity.Requirement;
import com.devplatform.lang.Result;


import java.util.Date;

public interface RequirementService {

    Result getRequirements(String founder,String project,Integer currentPage);

    Result getRequirements(String projectid,Integer currentPage);

    PageInfo findAllByPage(Integer currentPage , Integer size , String projectid);

    Result saveRequirement(String title, String founder,Date createDate,Date updateDate,String requirementhtml,String requirementtext,
                           String project_founder,String project);

    Result getInfo(String requirementid);

    Result deleteRequirement(String requirementid,String projectid);

    Result updateTitle(String requirementid, String projectid, String title, String updateDate, Integer currentPage);

    Result updateRequirement(Requirement requirement);
}
