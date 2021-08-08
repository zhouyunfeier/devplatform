package com.devplatform.controller;

import com.devplatform.lang.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/branch")
public class BranchController {


    @PostMapping("/getbranches")
    @ResponseBody
    public Result getBranches(@RequestParam("projectfounder") String projectfounder,@RequestParam("projectname") String projectname){
        System.out.println(projectfounder+"/"+projectname);

        return Result.success("成功了");
    }
}
