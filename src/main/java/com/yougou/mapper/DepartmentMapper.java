package com.yougou.mapper;

import com.yougou.pojo.Department;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DepartmentMapper extends Mapper<Department> {

    List<Department> getDepartment(Department department);

    Integer getDepartmentCount(Department department);
}
