package com.devplatform.mapper;


import com.devplatform.entity.Branch;
import com.devplatform.lang.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface BranchMapper {


    List<Branch> getBranches(@Param("projectfounder") String projectfounder,@Param("projectname") String projectname);

    int createBranch(@Param("projectid") String projectid,@Param("branchmessage") String branchmessage,
                     @Param("branchname") String branchname, @Param("branchstart") String branchstart,
                      @Param("branchid") String branchid,@Param("branchpath") String branchpath);

    int isExist(@Param("projectid") String projectid, @Param("branchname") String branchname);
}
