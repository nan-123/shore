package com.yougou.controller;

import com.alibaba.fastjson.JSONObject;
import com.yougou.service.IUploadExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping(value = "/uploadExcel")
@CrossOrigin
public class UploadExcelControl {

	 @Autowired
     private IUploadExcelService service;
    /** 
     * 描述：通过 jquery.form.js 插件提供的ajax方式上传文件 
     * @throws Exception
     */  
    @ResponseBody
    @RequestMapping("/ajaxUpload")
    public JSONObject ajaxUploadExcel(MultipartFile upfile) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Integer flag = 0;
        InputStream in =null;
        List<List<Object>> listob = null;
        if(upfile.isEmpty()){
            throw new Exception("文件不存在！");
        }

        in = upfile.getInputStream();
        flag = service.UploadExcel(in, upfile, listob);
        jsonObject.put("upload", flag);
        return jsonObject;
    }

    @RequestMapping("/excelOut")
    public void excelStandardTemplateOut(HttpServletResponse response) throws IOException{
        URL save = Thread.currentThread().getContextClassLoader().getResource("");
        String str =save.toString();
        str=str.substring(6,str.length());
        str=str.replaceAll("%20", " ");
        int num = str.lastIndexOf("shore"); //wgbs 为项目名，应用到不同的项目中，这个需要修改！
        str=str.substring(0, num+"shore".length());
        str = str +"/src/main/resources/template/userTemplate.xls"; //Excel模板所在的路径。
        File f = new File(str);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(("用户导入模板" + ".xlsx").getBytes(), "iso-8859-1"));//下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
}
