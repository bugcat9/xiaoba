package com.xiaoba.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

     boolean upload(MultipartFile file);

    /**
     * 将发送过来的 html内容转化为htnl文件存在服务器上
     * @param content
     * @return 在服务器上的地址
     */
     String writeToMd(String content,String filename);


     void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName);
}
