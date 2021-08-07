package com.devplatform.controller;
import cn.hutool.http.HttpResponse;
import com.devplatform.entity.EmailMessage;
import com.devplatform.entity.Project;
import com.devplatform.lang.Result;
import com.devplatform.service.FileService;
import com.devplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@Controller
@RequestMapping("/index")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    FileService fileService;


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
    public Result getMembers(@RequestParam("founder") String founder,@RequestParam("projectname") String projectname){
        return projectService.getMembers(founder,projectname);
    }

    @PostMapping("/projectinfo")
    @ResponseBody
    public Result getProjectInfo(@RequestParam("project") String project,@RequestParam("founder") String founder){
        return projectService.getProjectInfo(project,founder);
    }

    @PostMapping("/projectsecondinfo")
    @ResponseBody
    public Result getProjectSecondInfo(@RequestParam("path") String path) throws IOException {
        return projectService.getProjectSecondInfo(path);
    }
}
