package com.devplatform.service.impl;


import com.devplatform.email.CodeUtils;
import com.devplatform.entity.*;
import com.devplatform.lang.Result;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.mapper.UserMapper;
import com.devplatform.service.ProjectService;
import com.devplatform.util.GenerateID;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeUtils codeUtils;

    //静态文件夹目录
    private static final String static_root = "D:/devplatform_files";

    @Override
    public Result getProjects(Integer currentPage) {
        int size = 10;
        try {
            PageInfo<Project> pageInfo = findAllByPage(currentPage, size,null);
            return Result.success(pageInfo);
        }catch (Exception e){
            return Result.fail("获取项目信息失败");
        }
    }

    @Override
    public PageInfo findAllByPage(Integer currentPage , Integer size , String founder) {
        PageInfo<Project> pageInfo = new PageInfo<>();
        int totalCount = projectMapper.getCount(founder);

        if (totalCount == 0){
            return pageInfo;
        }
        pageInfo.setSize(size);
        pageInfo.setTotalCount(totalCount);

        int totalPage = (int)Math.ceil(totalCount/(double)size);
        pageInfo.setTotalPage(totalPage);

        if (currentPage < 1){
            currentPage = 1;
        }else if (currentPage > totalPage){
            currentPage = totalPage;
        }
        pageInfo.setCurrentPage(currentPage);
        int currentCount;
        if (currentPage == totalPage && (totalCount!= totalPage*size)){
            currentCount = totalCount % size;
        }else {
            currentCount = size;
        }
        int start = (currentPage - 1) * size;
        List<Project> list = projectMapper.findAllByPage(start,currentCount,founder);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public Result saveProject(Project project) {
        String projectid = "P"+ GenerateID.getGeneratID();
        project.setProjectid(projectid);
        project.setUpdateDate(project.getCreateDate());
        Team team = new Team();
        team.setProjectid(projectid);
        team.setStatus("管理员");
        team.setUsername(project.getFounder());
        team.setTeamid("T"+GenerateID.getGeneratID());
        try {
            //每个用户的项目不能同名，不同用户的不同项目可以同名
            String file_root = static_root + "/projects/"+project.getFounder()+"/"+project.getName()+"/blob/"+project.getName()+"-main";
            System.out.println(file_root);
            File file = new File(file_root);
            if (file.exists()){
                return Result.fail("已经存在该名字的项目");
            }
            if (!file.exists()){
                file.mkdirs();
            }
            projectMapper.saveProject(project);
            projectMapper.addMember(team);
            return Result.success("新建项目成功");
        }catch (Exception e) {
            return Result.fail("新建项目失败");
        }
    }

    @Override
    public Result getMyProjects(Integer currentPage,String founder) {
        int size = 10;
        try {
            PageInfo<Project> pageInfo = findAllByPage(currentPage,size,founder);
            return Result.success(pageInfo);
        }catch (Exception e){
            return Result.fail("获取项目信息失败");
        }
    }

    @Override
    public Result sendEmial(EmailMessage emailMessage) {
        String username = userMapper.getUserNameByEmial(emailMessage.getEmail());
        if (username == null){
            return Result.fail("用户不存在");
        }else {
            try {
                boolean flag = sendCode(emailMessage);
                if (flag){
                    return Result.success("邮件发送成功");
                }else {
                    return Result.fail("邮件发送失败");
                }
            }catch (Exception e){
                System.out.println("出错了");
                return Result.fail("邮件发送失败");
            }

        }
    }

    @Override
    public Result lookCode(String token) {
        boolean expire = eqToken(token);
        if (!expire){
            return Result.fail("凭证过期了");
        }else {
            try {
                EmailMessage emailMessage = codeUtils.findEmailMessage(token);
                Team team = new Team();
                String teamid = "T"+GenerateID.getGeneratID();
                team.setTeamid(teamid);
                team.setProjectid(emailMessage.getProjectid());
                String username = userMapper.getUserNameByEmial(emailMessage.getEmail());
                team.setUsername(username);
                team.setStatus("普通用户");
                return addMember(team);
            }catch (Exception e){
                return Result.fail("失败");
            }
        }
    }

    @Override
    public Result addMember(Team team) {
        try {
            projectMapper.addMember(team);
            return Result.success("验证成功");
        }catch (Exception e){
            return Result.fail("验证失败");
        }
    }

    @Override
    public boolean sendCode(EmailMessage emailMessage) {
        if (codeUtils.sendCode(emailMessage))
            return true;
        else
            return false;
    }

    @Override
    public boolean eqToken(String token) {
        boolean flag = codeUtils.eqToken(token);
        if (flag){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Result getMembers(String founder,String projectname) {
        try {
            List<Team> teams = projectMapper.getMembers(founder,projectname);
            return Result.success(teams);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取团队成员信息失败");
        }
    }

    @Override
    public Result getProjectInfo(String project,String founder) {
        try {
            List<Member> list = projectMapper.getMemberInfo(project,founder);
            String file_root = static_root+"/projects/"+founder+"/"+project+"/blob/"+project+"-main/";

            Map<String,Object> map = readFile(file_root);
            map.put("member_list",list);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取项目信息失败");
        }
    }

    @Override
    public Map<String,Object> readFile(String file_root) throws IOException {
        File main = new File(file_root);
        File[] lists = main.listFiles();
        List<FileItem> dir_list = new ArrayList<>();
        List<FileItem> file_list = new ArrayList<>();
        String readme = null;
        for (File file:lists){
            if (file.isDirectory()){
                FileItem dir_item = new FileItem();
                dir_item.setIsfile(false);
                dir_item.setFilename(file.getName());
                dir_item.setUpdateDate(file.lastModified());
                dir_item.setPath(file.getPath().substring(21));
                dir_list.add(dir_item);
            }else {
                FileItem file_item = new FileItem();
                file_item.setIsfile(true);
                file_item.setFilename(file.getName());
                file_item.setUpdateDate(file.lastModified());
                file_item.setPath(file.getPath().substring(21));
                file_list.add(file_item);
                if (file.getName().equals("README.md")){
                    String encoding = "UTF-8";
                    Long filelength = file.length();
                    byte[] filecontent = new byte[filelength.intValue()];
                    FileInputStream in = new FileInputStream(file);
                    in.read(filecontent);
                    in.close();
                    readme = new String(filecontent,encoding);
                    Parser parser = Parser.builder().build();
                    Node document = parser.parse(readme);
                    HtmlRenderer renderer = HtmlRenderer.builder().build();
                    readme = renderer.render(document);
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dir_list",dir_list);
        map.put("file_list",file_list);
        map.put("readme",readme);
        return map;
    }

    @Override
    public Result getProjectSecondInfo(String path) throws IOException {
        try {
            String file_root = static_root +"/"+ path;
            System.out.println(file_root);
            Map<String,Object> map = readFile(file_root);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("出错了");
        }
    }
}
