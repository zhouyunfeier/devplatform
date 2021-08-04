package com.devplatform.service;

import com.devplatform.entity.EmailMessage;
import com.devplatform.entity.PageInfo;
import com.devplatform.entity.Project;
import com.devplatform.entity.Team;
import com.devplatform.lang.Result;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface ProjectService {

    Result getProjects(Integer currentPage);

    PageInfo findAllByPage(Integer currentPage,Integer size, String founder);

    Result saveProject(Project project);

    Result getMyProjects(Integer currentPage, String founder);

    //用于发送邮件
    Result sendEmial(EmailMessage emailMessage);

    //用于进行邮箱验证
    Result lookCode(String token);

    Result addMember(Team team);

    boolean sendCode(EmailMessage emailMessage);

    boolean eqToken(String token);

    Result getMembers(String founder,String project);

    Result getProjectInfo(String project,String founder);

    Result getProjectSecondInfo(String path) throws IOException;

    public Map<String,Object> readFile(String file_root) throws IOException;
}
