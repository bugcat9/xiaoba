package com.xiaoba.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public boolean upload(MultipartFile file);

    /**
     * 将发送过来的 html内容转化为htnl文件存在服务器上
     * @param content
     * @return 在服务器上的地址
     */
    public String writeToHtml(String content,String title);
}
