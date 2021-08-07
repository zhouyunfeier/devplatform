package com.devplatform.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    void downloadMoreFile(String projectfounder, String projectname, String branch, HttpServletResponse response) throws IOException;
}
