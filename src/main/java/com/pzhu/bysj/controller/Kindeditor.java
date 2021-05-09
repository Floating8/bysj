package com.pzhu.bysj.controller;

import com.pzhu.bysj.pojo.KindEditorResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class Kindeditor {
    @RequestMapping("/uploadimg_kindeditor")
    @ResponseBody
    public KindEditorResult uploadimg_kindeditor(MultipartFile file, HttpServletRequest request){
        KindEditorResult result=null;
        try {
            String oldFileName= file.getOriginalFilename();
            System.out.println("要上传的原始文件的名字是:"+oldFileName);  // 123435.jpg
            //因为原始的文件名不能直接使用，在服务器存储的时候会覆盖，所以需要随机一个文件名
            String newFileName= UUID.randomUUID().toString()+"."+oldFileName.split("\\.")[1];
            //获取服务器真实的上传路径
            String filePath=request.getServletContext().getRealPath("/images/news")+"/"+newFileName;
            System.out.println("服务器上传的路径是:"+filePath);
            //开始上传
            file.transferTo(new File(filePath));
            System.out.println("文件上传成功，名字为:"+newFileName);
            result= new KindEditorResult(0,"/images/news/"+newFileName,"");
        } catch (IOException e) {
            result= new KindEditorResult(1,"","文件上传失败");
        }
        return result;
    }
}
