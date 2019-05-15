package com.yougou.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface IUploadExcelService {

	 void UploadExcel(InputStream in, MultipartFile file, List<List<Object>> listob, HttpServletResponse response);

}
