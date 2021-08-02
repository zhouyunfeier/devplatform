package com.devplatform.controller;
import com.devplatform.entity.EmailMessage;
import com.devplatform.entity.Project;
import com.devplatform.lang.Result;
import com.devplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/index")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/project")
    @ResponseBody
    public Result getProjects(@RequestParam("currentPage") Integer currentPage){
        return projectService.getProjects(currentPage);
    }

    @PostMapping("/saveproject")
    @ResponseBody
    public Result saveProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }

    @PostMapping("/myproject")
    @ResponseBody
    public Result getMyProjects(@RequestParam("currentPage") Integer currentPage , @RequestParam("founder") String founder){
        return projectService.getMyProjects(currentPage,founder);
    }

    @PostMapping("/sendemail")
    @ResponseBody
    public Result addMember(@RequestBody EmailMessage emailMessage){
        return projectService.sendEmial(emailMessage);
    }

    @GetMapping("/lookCode/{token}")
    public Result lookCode(@PathVariable("token") String token){
        System.out.println("访问了token");
        System.out.println(token);
        return projectService.lookCode(token);
    }

    @PostMapping("/getmembers")
    @ResponseBody
    public Result getMembers(@RequestParam("projectid") String projectid){
        System.out.println(projectid);
        return projectService.getMembers(projectid);
    }

    @PostMapping("/projectinfo")
    @ResponseBody
    public Result getProjectInfo(@RequestParam("projectid") String projectid){
        return projectService.getProjectInfo(projectid);
    }
}
