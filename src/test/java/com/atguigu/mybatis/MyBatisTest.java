package com.atguigu.mybatis;

import com.atguigu.dao.EmployeeMapper;
import com.atguigu.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void employeeTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee employee = sqlSession.selectOne("com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void employeeMapperTest() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        System.out.println(sqlSession.getMapper(EmployeeMapper.class).getEmpById(1));
        sqlSession.close();
    }

    @Test
    public void employeeMapperAnnotation() throws IOException{
        System.out.println(getSqlSessionFactory().openSession().getMapper(EmployeeMapperAnnotation.class).getEmpById(1));
    }

}
