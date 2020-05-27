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
import org.springframework.web.bind.annotation.RestController;
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

    private String url = "http://39.99.203.80:8080/images/";
    @RequestMapping("/upload")
    @ResponseBody
    public String upload (@RequestParam("file") MultipartFile file) {

        boolean result = fileService.upload(file);
        if (result){
            return url+file.getOriginalFilename();
        }
        return "上传失败";
    }

    @RequestMapping("/html")
    @ResponseBody
    public String saveHtml(String content,String title){
        String filename = fileService.writeToHtml(content, title);
        if (filename!=null){
            return url+filename;
        }
        return "编写失败";
    }

}
