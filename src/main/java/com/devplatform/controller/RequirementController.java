package com.devplatform.controller;


import com.devplatform.entity.Requirement;
import com.devplatform.lang.Result;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.service.MilestoneService;
import com.devplatform.service.ProjectService;
import com.devplatform.service.RequirementService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/index")
public class RequirementController {

    @Autowired
    RequirementService requirementService;

    @ResponseBody
    @PostMapping("/requirement")
    public Result getRequirements(@RequestParam("founder") String founder,@RequestParam("project") String project, @RequestParam("currentPage") Integer currentPage){
        return requirementService.getRequirements(founder,project,currentPage);
    }

    @ResponseBody
    @PostMapping("/saverequirement")
    public Result saveRequirement(@RequestParam("title") String title,
                                  @RequestParam("founder") String founder,
                                  @RequestParam("createDate") Date createDate,
                                  @RequestParam("updateDate") Date updateDate,
                                  @RequestParam("requirementhtml") String requirementhtml,
                                  @RequestParam("requirementtext") String requirementtext,
                                  @RequestParam("project_founder") String project_founder,
                                  @RequestParam("project") String project){
        return requirementService.saveRequirement(title,founder,createDate,updateDate,requirementhtml,requirementtext,project_founder,project);
    }

    @ResponseBody
    @PostMapping("/getinfo")
    public Result getInfo(@RequestParam("requirementid") String requirementid){
        return requirementService.getInfo(requirementid);
    }

    @ResponseBody
    @PostMapping("/requirement/delete")
    public Result deleteRequirement(@RequestParam("requirementid") String requirementid,@RequestParam("projectid") String projectid){
        System.out.println("///"+projectid);
        return requirementService.deleteRequirement(requirementid,projectid);
    }

    @ResponseBody
    @PostMapping("/requirement/updatetitle")
    public Result updateTitle(@RequestParam("requirementid") String requirementid,
                              @RequestParam("projectid") String projectid,
                              @RequestParam("title") String title,
                              @RequestParam("updateDate") String updateDate,
                              @RequestParam("currentPage") Integer currentPage){
        return requirementService.updateTitle(requirementid,projectid,title,updateDate,currentPage);
    }

    @ResponseBody
    @PostMapping("/requirement/updaterequirement")
    public Result updateRequirement(@RequestBody Requirement requirement){
        return requirementService.updateRequirement(requirement);
    }


}
