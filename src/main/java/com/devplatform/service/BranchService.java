package com.devplatform.service;

import com.devplatform.lang.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface BranchService {

    Result getBranches(String projectfounder, String projectname);

    Result createBranch(String projectfounder, String projectname,String branchname,String branchstart,String branchmessage);

}
