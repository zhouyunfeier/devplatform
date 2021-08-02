package com.devplatform.controller;


import cn.hutool.core.io.FileUtil;
import com.devplatform.entity.User;
import com.devplatform.lang.Result;
import com.devplatform.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    @ResponseBody
    @GetMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("成功登出");
    }

    @ResponseBody
    @GetMapping(value = "/authentication")
    public String authentication(){
        return "身份认证成功";
    }


    @ResponseBody
    @PostMapping("/updateavatar")
    public Result updateAvatar(@RequestParam("username") String username,@RequestParam("file") MultipartFile file) throws IOException {
        return userService.uploadAvatar(username,file);
    }

    @ResponseBody
    @PostMapping("/updatepassword")
    public Result updatePassword(@RequestParam("username") String username,@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword){
        return userService.updatePassword(username,oldpassword,newpassword);
    }

    @ResponseBody
    @PostMapping("/userinfo")
    public Result userinfo (@RequestParam("username") String username){
        return userService.userInfo(username);
    }

    @ResponseBody
    @PostMapping("updateuserinfo")
    public Result updateinfo(@RequestParam("username") String username,@RequestParam("email") String email){
        return userService.updateInfo(username,email);
    }
}
