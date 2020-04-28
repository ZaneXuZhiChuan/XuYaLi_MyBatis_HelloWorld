package com.atguigu.dao;

import com.atguigu.mybatis.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from tb1_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
