package com.yougou.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yougou.mapper.DepartmentMapper;
import com.yougou.pojo.Department;
import com.yougou.service.IDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/dept")
@CrossOrigin
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    @Resource
    private DepartmentMapper departmentMapper;



    @RequestMapping("/getDept")
    @ResponseBody
    JSONObject getDept(Department dept){
        if (dept == null) {
            dept = new Department();
        }
        JSONObject jsonObject = new JSONObject();
        List<Department> list = departmentService.getDepartment(dept);
        Integer departmentCount = departmentService.getDepartmentCount(dept);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",departmentCount);
        jsonObject.put("data",array);
        return  jsonObject;
    }

}
