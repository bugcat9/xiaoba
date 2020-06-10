package com.xiaoba.service.impl;

import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author zhouning
 */
@Service
public class FileServiceImpl implements FileService {

    private Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    @Value("${filepath}")
    private String filepath;



    @Override
    public boolean upload(MultipartFile file) {
        // 获取原始名字
        String fileName = file.getOriginalFilename();

        // 文件重命名，防止重复,在这边先不判断文件是否重复
        //fileName = filepath + UUID.randomUUID() + fileName;
        fileName = filepath + fileName;
        System.out.println(fileName);
        logger.info("托盘存放位置"+fileName);

        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            // 保存到服务器中
            file.transferTo(dest);
            return true;
        } catch (Exception e) {
            logger.error("文件上传错误:",e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String writeToMd(String content,String filename) {
        String fileName = filename+".md";
        String pathName = filepath + fileName;
        try {
            PrintWriter pWriter = new PrintWriter(new FileOutputStream(new File(pathName)));
            //写下中间内容
            pWriter.println(content);
            pWriter.close();
            logger.info("编写了文章",pathName);
        } catch (FileNotFoundException e) {
            logger.error("存储为md文件错误:",e);
            e.printStackTrace();
        }catch (Exception e){
            logger.error("存储为md文件错误:",e);
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + fileName);
        // 文件地址，真实环境是存放在数据库中的
        File file = new File(filepath+'/'+fileName);
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

    @Override
    public void deletFile(String fileName) {
        String pathName = filepath + fileName;
        File file = new File(pathName);
        if (file.exists()){
            file.delete();
        }
    }
}
