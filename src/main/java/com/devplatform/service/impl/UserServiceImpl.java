package com.devplatform.service.impl;
import cn.hutool.core.io.FileUtil;
import com.devplatform.entity.User;
import com.devplatform.lang.Result;
import com.devplatform.mapper.UserMapper;
import com.devplatform.service.UserService;
import com.devplatform.util.GenerateID;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;


@Service
public class UserServiceImpl implements UserService {

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    private static final String static_root = "D:/devplatform_files";

    @Autowired
    UserMapper userMapper;


    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username){
        return userMapper.getUserByUsername(username);
    }

    public User getByNameAndPassword(String username , String password){
        return userMapper.getByUsernameAndPassword(username,password);
    }

    public void add(User user){
        userMapper.save(user);
        return;
    }

    @Override
    public boolean EmailisExist(String email) {
        User user = userMapper.getUserByEmail(email);
        return null!=user;
    }

    @Override
    public Result login(User user) {
        String username = user.getUsername();
        String avatar = userMapper.getAvatarByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        user.setAvatar(avatar);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,user.getPassword());
        try {
            subject.login(usernamePasswordToken);
            usernamePasswordToken.setRememberMe(true);
            return Result.success(user);
        }catch (AuthenticationException e){
            return Result.fail("??????????????????");
        }
    }

    @Override
    public Result register(User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            username = HtmlUtils.htmlEscape(username);
            user.setUsername(username);

            boolean name_exist = isExist(username);
            boolean emial_exist = EmailisExist(email);

            if (name_exist){
                return Result.fail("?????????????????????");
            }
            if (emial_exist){
                return Result.fail("??????????????????");
            }

            String userid = "U"+GenerateID.getGeneratID();
            user.setUserid(userid);
            user.setAvatar("http://localhost:8080/avatar/e105b3db2b78b553bad52ded8514f4c6.jpg");
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String encodePassword = new SimpleHash("md5",password,salt,times).toString();
            user.setSalt(salt);
            user.setPassword(encodePassword);

            add(user);
            return Result.success(user);

        }catch (Exception e){
            System.out.println(e);
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result uploadAvatar(String username, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();                                                //????????????????????????
//        String rootFilePath = System.getProperty("user.dir")+"/src/main/resources/files/"+originalFilename;  //?????????????????????
        String rootFilePath = static_root +"/avatar/"+ originalFilename;
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        //???????????????????????????
        String avatar = ip+":"+port+"/avatar/"+originalFilename;
        try{
            userMapper.updateAvatar(avatar,username);
            return Result.success(200,"????????????",avatar);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("????????????");
        }
    }

    @Override
    public Result updatePassword(String username, String oldpassword, String newpaswword) {
        User user = userMapper.getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            return Result.fail("???????????????");
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        int times = 2;
        String oldEncodePassword = new SimpleHash("md5",oldpassword,salt,times).toString();
        if (user.getPassword().equals(oldEncodePassword)){
            try {
                String password = new SimpleHash("md5",newpaswword,salt,times).toString();
                userMapper.updatePassword(username,password);
                return Result.success("??????????????????");
            }catch (Exception e){
                System.out.println(e);
                return Result.fail("??????????????????");
            }
        }
        return Result.fail("??????????????????");
    }

    @Override
    public Result updateInfo(String username, String email) {
        try {
            int flag = userMapper.IfEmailisExist(email);
            if (flag != 0){
                return Result.fail("??????????????????");
            }else {
                userMapper.updatePassword(username,email);
                return Result.success("????????????");
            }
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("????????????");
        }
    }

    @Override
    public Result userInfo(String username) {
        try{
            User user = userMapper.getUserByUsername(username);
            return Result.success(200,"????????????",user.getEmail());
        }catch (Exception e){
            return Result.fail("????????????");
        }
    }
}
