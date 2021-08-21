package com.devplatform.service.impl;

import com.devplatform.entity.Branch;
import com.devplatform.lang.Result;
import com.devplatform.mapper.BranchMapper;
import com.devplatform.mapper.ProjectMapper;
import com.devplatform.service.BranchService;
import com.devplatform.util.FileUtils;
import com.devplatform.util.GenerateID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    //静态文件夹目录
    private static final String static_root = "D:/devplatform_files/projects/";


    private static final String Blob = "blob";

    @Autowired
    BranchMapper branchMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Result getBranches(String projectfounder, String projectname) {
        try {
            List<Branch> list = branchMapper.getBranches(projectfounder,projectname);
            return Result.success(list);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("获取分支信息失败");
        }
    }

    @Override
    public Result createBranch(String projectfounder, String projectname, String branchname, String branchstart, String branchmessage) {
        try {
            String from = static_root + projectfounder + File.separator + projectname + File.separator + Blob + File.separator + projectname +"-"+branchstart;
            String to = static_root + projectfounder + File.separator + projectname + File.separator + Blob + File.separator + projectname + "-"+branchname;
            String branchpath = to;
            String projectid = projectMapper.getProjectidByNameAndFounder(projectfounder,projectname);
            String branchid = "B"+GenerateID.getGeneratID();
            if (branchMapper.isExist(projectid,branchname) != 0) {
                branchMapper.createBranch(projectid, branchmessage, branchname, branchstart, branchid, to);
                File from_file = new File(from);
                File to_file = new File(to);
                to_file.mkdirs();
                FileUtils.printFile(from_file, to_file);
            }else {
                return Result.fail("该分支已经存在了");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("");
    }

}
