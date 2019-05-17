package com.yougou.service.impl;

import com.yougou.mapper.UserMapper;
import com.yougou.pojo.User;
import com.yougou.service.IUploadExcelService;
import com.yougou.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UploadExcelServiceImpl implements IUploadExcelService {
	
	 @Autowired
	 private UserMapper userMapper;

	@Override
	public Integer UploadExcel(InputStream in, MultipartFile file, List<List<Object>> listob) {
		Integer flag = 0;
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
		} catch (Exception e) {
			flag = 1;
		}
		return flag;
	}

	
	

}
