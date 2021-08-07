package com.devplatform.service.impl;
import com.devplatform.service.FileService;
import com.devplatform.util.ZipUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileServiceImpl implements FileService {
    //静态文件夹目录
    private static final String static_root = "D:/devplatform_files";

    @Override
    public void downloadMoreFile(String projectfounder, String projectname, String branch, HttpServletResponse response) throws IOException {
        String file_root = static_root + "/projects/"+projectfounder+"/"+projectname+"/blob/"+projectname+"-"+branch;
        response.setHeader("Content-Disposition","attachment;filename="+projectname+"-main");
        OutputStream outputStream = response.getOutputStream();
        ZipUtils.toZip(file_root,outputStream);
    }
}
