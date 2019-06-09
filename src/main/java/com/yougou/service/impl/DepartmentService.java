package com.yougou.service.impl;

import com.yougou.mapper.DepartmentMapper;
import com.yougou.pojo.Department;
import com.yougou.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Department> getDepeNode() {
        Department department = new Department();
        List<Department> list = getDepartment(department);
        return makeTree(list, 0);
    }

    @Override
    public Integer delDept(String id) {
        Department department = new Department();
        department.setId(Integer.valueOf(id));
        Department node = departmentMapper.selectByPrimaryKey(department);
        if (node != null){
            if (node.getPid().equals("0")){
                return 1;
            }
            // 获取子部门删除
            Department childNode = new Department();
            childNode.setPid(id);
            List<Department> childNodes = getDepartment(childNode);
            if (childNodes != null && childNodes.size() > 0){
                for (Department dp : childNodes) {
                    departmentMapper.delete(dp);
                }
            }

            departmentMapper.delete(node);
        }

        return 0;

    }

    private  List<Department> makeTree(List<Department> departmentList, int pId) {

        //子类
        List<Department> children = departmentList.stream().filter(x -> Integer.valueOf(x.getPid()) == pId).collect(Collectors.toList());

        //后辈中的非子类
        List<Department> successor = departmentList.stream().filter(x -> Integer.valueOf(x.getPid()) != pId).collect(Collectors.toList());

        children.forEach(x ->
                {
                    makeTree(successor, x.getId()).forEach(
                            y -> x.getChildren().add(y)
                    );
                }
        );

        return children;

    }
}
