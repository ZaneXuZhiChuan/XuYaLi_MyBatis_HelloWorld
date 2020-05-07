package com.atguigu.dao;

import com.atguigu.mybatis.Employee;

public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    int insertEmp(Employee employee);
}
