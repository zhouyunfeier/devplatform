package com.devplatform.mapper;

import com.devplatform.entity.Requirement;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RequirementMapper {

    int getCountByProjectid(@Param("projectid") String projectid);

    List<Requirement> findAllByPage(@Param("start") Integer start,@Param("size") Integer size,@Param("projectid") String projectid);

    int saveRequirement(Requirement requirement);

    Requirement getInfo(@Param("requirementid") String requirementid);

    int deleteRequirement(@Param("requirementid") String requirementid);

    int updateTitle(@Param("requirementid") String requirementid, @Param("title") String title, @Param("updateDate") Date updateDate);

    int updateRequirement(Requirement requirement);
}
