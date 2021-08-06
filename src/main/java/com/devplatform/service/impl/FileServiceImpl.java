package com.devplatform.service.impl;
import com.devplatform.service.FileService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class FileServiceImpl implements FileService {
    //静态文件夹目录
    private static final String static_root = "D:/devplatform_files";

    @Override
    public void downloadMoreFile(String projectfounder, String projectname, String branch, HttpServletResponse response) {
        String file_root = static_root + "/projects/"+projectfounder+"/"+projectname+"/blob/"+branch;

    }
}
