package com.xiaoba.controller;

import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    @RequestMapping("/md")
    @ResponseBody
    public String saveMd(String content,String title){
        String filename = fileService.writeToMd(content, title);
        if (filename!=null){
            return url+filename;
        }
        return "编写失败";
    }

    /**
     * 下载文件
     * @param request
     * @param response
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进行下载");

        // 文件名
        String fileName = "timg.jpg";
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + fileName);
        // 文件地址，真实环境是存放在数据库中的
        File file = new File("E:\\home\\images\\timg.jpg");
        // 穿件输入对象
        FileInputStream fis = null;
        // 创建输出对象
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
