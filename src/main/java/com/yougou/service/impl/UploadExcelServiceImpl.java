package com.yougou.service.impl;

import com.yougou.mapper.UserMapper;
import com.yougou.pojo.User;
import com.yougou.service.IUploadExcelService;
import com.yougou.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UploadExcelServiceImpl implements IUploadExcelService {
	
	 @Autowired
	 private UserMapper userMapper;

	@Override
	public void UploadExcel(InputStream in, MultipartFile file, List<List<Object>> listob, HttpServletResponse response) {
		try {
			listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
			  for (int i = 0; i < listob.size(); i++) {
				  List<Object> lo = listob.get(i);
					  User user =new User();
					  user.setName(String.valueOf(lo.get(0)==null?null:lo.get(0)));
					   user.setAge(String.valueOf(lo.get(1)==null?null:lo.get(1)));
					   user.setLoginName(String.valueOf(lo.get(2)==null?null:lo.get(2)));
					   user.setPwd(String.valueOf(lo.get(3)==null?null:lo.get(3)));
					   user.setDept(String.valueOf(lo.get(4)==null?null:lo.get(4)));
					   user.setSex(String.valueOf(lo.get(5)==null?null:lo.get(5)));
					   user.setMail(String.valueOf(lo.get(6)==null?null:lo.get(6)));
					  long nowDate = new Date().getTime();
					  String sid = String.valueOf(nowDate);
					  user.setId(sid);
					  userMapper.insert(user );
			  }
			  PrintWriter out = null;
		        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
		        out = response.getWriter();  
		        out.print("文件导入成功！");  
		        out.flush();  
		        out.close();  
			
		} catch (Exception e) {
			e.printStackTrace();
			 PrintWriter out = null;  
		        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
		        try {
					out = response.getWriter();
				} catch (IOException e1) {
					e1.printStackTrace();
				}  
		        out.print("文件导入失败！");
		        out.flush();  
		        out.close(); 
		        throw new RuntimeException(e);
		   
		} 
		
		
	}

	
	

}
