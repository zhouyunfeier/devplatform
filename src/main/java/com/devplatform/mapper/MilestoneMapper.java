package com.devplatform.mapper;

import com.devplatform.entity.Milestone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface MilestoneMapper {

    int getAllCountByProjectid(String projectid);

    Collection<Milestone> getAllMilestoneByProjectid(String projectid);

    int saveMilestone(Milestone milestone);

}
