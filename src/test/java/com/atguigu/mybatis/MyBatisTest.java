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
import java.util.ArrayList;
import java.util.List;

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
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
        sqlSession.close();
    }

    @Test
    public void employeeMapperAnnotation() throws IOException{
        System.out.println(getSqlSessionFactory().openSession().getMapper(EmployeeMapperAnnotation.class).getEmpById(1));
    }

    @Test
    public void insertEmpTest()throws IOException{
//        当我们不给JavaBean中的ID赋值时候可以通过在mapper.xml文件中设置useGeneratedKeys="true"
//        和keyProperty="id"来获取数据库中自增主键的值
        Employee zhouJianPing02 = new Employee(
//                null,
                "ZhouJianPing05",
                "ZhouJianPing@gmail05.com",
                "1"
        );
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int i = sqlSession.getMapper(EmployeeMapper.class).insertEmp(
                zhouJianPing02);
        System.out.println("Before commit:"+zhouJianPing02.getId());
        sqlSession.commit();
        System.out.println("After commit:"+zhouJianPing02.getId());
        sqlSession.close();
        System.out.println("Close:"+zhouJianPing02.getId());
    }
}
