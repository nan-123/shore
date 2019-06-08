package com.yougou.service.impl;

import com.yougou.mapper.DepartmentMapper;
import com.yougou.pojo.Department;
import com.yougou.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartment(Department department) {
        return departmentMapper.getDepartment(department);
    }

    @Override
    public Integer getDepartmentCount(Department department) {
        return departmentMapper.getDepartmentCount(department);
    }
}
