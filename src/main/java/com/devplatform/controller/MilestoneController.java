package com.devplatform.controller;

import com.devplatform.entity.Milestone;
import com.devplatform.lang.Result;
import com.devplatform.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/index")
public class MilestoneController {

    @Autowired
    MilestoneService milestoneService;

    @PostMapping("/milestone")
    @ResponseBody
    public Result getMilestone(@RequestParam("projectfounder") String projectfounder,@RequestParam("projectname") String projectname){
        return milestoneService.getAllMilestoneByProjectid(projectfounder,projectname);
    }

    @PostMapping("/savemilestone")
    @ResponseBody
    public Result saveMilestone(@RequestBody Milestone milestone){
        System.out.println("tttttttt:"+milestone.getProjectfounder());
        return milestoneService.saveMilestone(milestone);
    }
}
