package com.devplatform.service.impl;


import com.devplatform.email.CodeUtils;
import com.devplatform.entity.*;
import com.devplatform.lang.Result;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.mapper.UserMapper;
import com.devplatform.service.ProjectService;
import com.devplatform.util.GenerateID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeUtils codeUtils;

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
    public Result getMembers(String projectid) {
        try {
            List<Team> teams = projectMapper.getMembers(projectid);
            return Result.success(teams);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取团队成员信息失败");
        }
    }

    @Override
    public Result getProjectInfo(String projectid) {
        try {
            List<Member> list = projectMapper.getMemberInfo(projectid);
            System.out.println(list);
            return Result.success(list);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取项目信息失败");
        }
    }
}
