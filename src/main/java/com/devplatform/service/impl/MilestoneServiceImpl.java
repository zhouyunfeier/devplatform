package com.devplatform.service.impl;


import com.devplatform.entity.Milestone;
import com.devplatform.lang.Result;
import com.devplatform.mapper.MilestoneMapper;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.service.MilestoneService;
import com.devplatform.util.GenerateID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.sql.Date;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    @Autowired
    MilestoneMapper milestoneMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Result getAllMilestoneByProjectid(String projectfounder,String projectname) {
        try {
            Collection<Milestone> milestones = milestoneMapper.getAllMilestoneByProjectid(projectfounder,projectname);
            return Result.success(milestones);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取里程碑失败");
        }
    }

    @Override
    public Result saveMilestone(Milestone milestone) {
        try{

            milestone.setPercentage(0);
            milestone.setStatus("执行中");
            String milestoneid = "M"+GenerateID.getGeneratID();
            milestone.setMilestoneid(milestoneid);
            milestoneMapper.saveMilestone(milestone);
            return Result.success("新建里程碑成功");
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("新建里程碑失败");
        }
    }
}
