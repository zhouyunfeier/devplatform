package com.devplatform.mapper;

import com.devplatform.entity.Milestone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface MilestoneMapper {

    int getAllCountByProjectid(String founder,String project);

    Collection<Milestone> getAllMilestoneByProjectid(@Param("projectfounder") String projectfounder,@Param("projectname") String projectname);

    int saveMilestone(Milestone milestone);

}
