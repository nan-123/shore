package com.yougou.service;

import com.yougou.pojo.Department;

import java.util.List;


public interface IDepartmentService {
    List<Department> getDepartment(Department department);

    Integer getDepartmentCount(Department department);

    List<Department> getDepeNode();
}
