package com.devplatform.mapper;

import com.devplatform.entity.Member;
import com.devplatform.entity.Project;
import com.devplatform.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    List<Project> findAllByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("founder") String founder);

    int getCount(@Param("founder") String founder);

    int saveProject(Project project);

    int addMember(Team team);

    List<Team> getMembers(@Param("projectid") String projectid);

    List<Member> getMemberInfo(@Param("projectid") String projectid);
}
