package com.yougou.controller;

import com.yougou.service.IUploadExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/uploadExcel")
@CrossOrigin
public class UploadExcelControl {

	 @Autowired
     private IUploadExcelService service;
    /** 
     * 描述：通过 jquery.form.js 插件提供的ajax方式上传文件 
     * @param response
     * @throws Exception 
     */  
    @ResponseBody
    @RequestMapping("/ajaxUpload")
    public  void  ajaxUploadExcel(MultipartFile photo, HttpServletResponse response) throws Exception {
//    	System.out.println("能到达这里！");
//    	MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//    	MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
//
//        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");
//
        InputStream in =null;
        List<List<Object>> listob = null;
//        MultipartFile file = multipartRequest.getFile("upfile");
        if(photo.isEmpty()){
            throw new Exception("文件不存在！");
        }  
          
        in = photo.getInputStream();
       service.UploadExcel(in, photo, listob, response);
    }  

}
