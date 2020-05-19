package com.ross.restfulcrud;

import com.ross.restfulcrud.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@MapperScan("com.ross.restfulcrud.mapper")
@SpringBootTest
class RestfulCrudApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//        connection.close();

        //测试mybatis
//        System.out.println(departmentMapper.selectByPrimaryKey(1).getDepartmentname());
    }

}
