package com.devplatform.controller;

import com.devplatform.entity.Chunk;
import com.devplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    //静态文件夹目录
    private static final String static_root = "D:/devplatform_files/projects";

    @Autowired
    FileService fileService;

    @PostMapping("/downloadproject")
    @ResponseBody
    public void downloadProject(@RequestParam("projectfounder") String projectfounder,
                                @RequestParam("projectname") String projectname,
                                @RequestParam("branch") String branch, HttpServletResponse response) throws IOException {
        fileService.downloadMoreFile(projectfounder,projectname,branch,response);
    }

    @PostMapping("/single")
    @ResponseBody
    public void singleUpload(Chunk chunk,HttpServletRequest request){
        String file_root = static_root+"/"+request.getParameter("path");
        System.out.println(file_root);
        MultipartFile file = chunk.getFile();
        String filename = chunk.getFilename();
        file_root = file_root + "/" + chunk.getRelativePath();
        file_root = file_root.substring(0,file_root.length() - filename.length());
        System.out.println(file_root);

        try{
            byte[] bytes = file.getBytes();
            if (!Files.isWritable(Paths.get(file_root))){
                Files.createDirectories(Paths.get(file_root));
            }
            Path path = Paths.get(file_root,filename);
            Files.write(path,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
