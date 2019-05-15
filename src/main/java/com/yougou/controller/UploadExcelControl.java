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
    public  void  ajaxUploadExcel(MultipartFile upfile, HttpServletResponse response) throws Exception {
        InputStream in =null;
        List<List<Object>> listob = null;
        if(upfile.isEmpty()){
            throw new Exception("文件不存在！");
        }  
          
        in = upfile.getInputStream();
       service.UploadExcel(in, upfile, listob, response);
    }  

}
