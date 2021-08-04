package com.devplatform.service.impl;


import com.devplatform.entity.PageInfo;
import com.devplatform.entity.Requirement;
import com.devplatform.lang.Result;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.mapper.RequirementMapper;
import com.devplatform.service.RequirementService;
import com.devplatform.util.GenerateID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    RequirementMapper requirementMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Result getRequirements(String founder,String project, Integer currentPage) {
            int size = 10;
            try {
                String projectid = projectMapper.getProjectidByNameAndFounder(founder,project);
                PageInfo<Requirement> pageInfo = findAllByPage(currentPage, size, projectid);
                return Result.success(pageInfo);
            }catch (Exception e){
                System.out.println(e);
                return Result.fail("获取需求信息失败");
            }
    }

    @Override
    public Result getRequirements(String projectid, Integer currentPage) {
        int size = 10;
        try {
            PageInfo<Requirement> pageInfo = findAllByPage(currentPage, size, projectid);
            return Result.success(pageInfo);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取需求信息失败");
        }
    }

    @Override
    public PageInfo findAllByPage(Integer currentPage , Integer size , String projectid) {
        PageInfo<Requirement> pageInfo = new PageInfo<>();
        int totalCount = requirementMapper.getCountByProjectid(projectid);
        if (totalCount == 0) {
            return pageInfo;
        }
        pageInfo.setSize(size);
        pageInfo.setTotalCount(totalCount);

        int totalPage = (int) Math.ceil(totalCount / (double) size);
        pageInfo.setTotalPage(totalPage);

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        pageInfo.setCurrentPage(currentPage);

        int currentCount;
        if (currentPage == totalPage && (totalCount != totalPage * size)) {
            currentCount = totalCount % size;
        } else {
            currentCount = size;
        }
        int start = (currentPage - 1) * size;

        List<Requirement> list = requirementMapper.findAllByPage(start, currentCount, projectid);

        pageInfo.setList(list);

        return pageInfo;

    }

    @Override
    public Result saveRequirement(String title, String founder, Date createDate, Date updateDate, String requirementhtml, String requirementtext,
                                  String project_founder, String project){
        String requirementid = GenerateID.getGeneratID();
        requirementid = "R" + requirementid;
        String projectid = projectMapper.getProjectidByNameAndFounder(project_founder,project);
        System.out.println("projectid:"+projectid);
        Requirement requirement = new Requirement();
        requirement.setRequirementid(requirementid);
        requirement.setFounder(founder);
        requirement.setProjectid(projectid);
        requirement.setRequirementtext(requirementtext);
        requirement.setRequirementhtml(requirementhtml);
        requirement.setUpdateDate(updateDate);
        requirement.setCreateDate(createDate);
        requirement.setTitle(title);
        try {
            requirementMapper.saveRequirement(requirement);
            return getRequirements(requirement.getProjectid(),1);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("保存失败");
        }
    }

    @Override
    public Result getInfo(String requirementid) {
        try {
            Requirement requirement = requirementMapper.getInfo(requirementid);
            return Result.success(requirement);

        }catch (Exception e){
            System.out.println(e);
            return Result.fail("错误的");
        }
    }

    @Override
    public Result deleteRequirement(String requirementid,String projectid) {
        try {
            requirementMapper.deleteRequirement(requirementid);
            return getRequirements(projectid,1);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("需求删除失败");
        }
    }

    @Override
    public Result updateTitle(String requirementid, String projectid, String title, String updateDate, Integer currentPage) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(updateDate);
            requirementMapper.updateTitle(requirementid,title,date);
            return getRequirements(projectid,currentPage);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("更新需求标题失败");
        }
    }

    @Override
    public Result updateRequirement(Requirement requirement) {
        try {
            requirementMapper.updateRequirement(requirement);
            return Result.success("更新成功");
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("更新失败");
        }
    }
}
