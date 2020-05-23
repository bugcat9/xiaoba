package com.xiaoba.controller;

import com.xiaoba.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author zhouning
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping("/")
    public String index(){
        return "upload";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String upload (@RequestParam("file") MultipartFile file) {

        boolean result = fileService.upload(file);
        if (result){
            return "http://39.99.203.80:8081/images/"+file.getOriginalFilename();
        }

        return "上传失败";
    }



}
