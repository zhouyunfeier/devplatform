package com.devplatform.service;

import com.devplatform.entity.User;
import com.devplatform.lang.Result;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {

    //根据用户名判断用户是否存在
    public boolean isExist(String username);

    //根据用户名查找用户
    public User getByName(String username);

    //根据用户名和密码查找用户
    public User getByNameAndPassword(String username , String password);

    //添加用户
    public void add(User user);

    //根据邮箱判断用户是否存在
    public boolean EmailisExist(String email);


    //用户登录验证
    public Result login(User user);

    //用户注册
    public Result register(User user);

    public Result uploadAvatar(String username, MultipartFile file) throws IOException;

    public Result updatePassword(String username,String oldpassword,String newpaswword);

    public Result updateInfo(String username,String email);

    Result userInfo(String username);
}
