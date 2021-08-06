package com.devplatform.service;

import javax.servlet.http.HttpServletResponse;

public interface FileService {

    void downloadMoreFile(String projectfounder, String projectname, String branch, HttpServletResponse response);
}
