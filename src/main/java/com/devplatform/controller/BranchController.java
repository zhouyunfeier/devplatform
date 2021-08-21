package com.devplatform.controller;

import com.devplatform.lang.Result;
import com.devplatform.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    BranchService branchService;

    @PostMapping("/getbranches")
    @ResponseBody
    public Result getBranches(@RequestParam("projectfounder") String projectfounder,@RequestParam("projectname") String projectname){
        return branchService.getBranches(projectfounder,projectname);
    }

    @PostMapping("/createbranch")
    @ResponseBody
    public Result createBranch(@RequestParam("projectfounder") String projectfounder,
                               @RequestParam("projectname") String projectname,
                               @RequestParam("branchname") String branchname,
                               @RequestParam("branchstart") String branchstart,
                               @RequestParam("branchmessage") String branchmessage){
        return branchService.createBranch(projectfounder, projectname, branchname, branchstart, branchmessage);
    }
}
