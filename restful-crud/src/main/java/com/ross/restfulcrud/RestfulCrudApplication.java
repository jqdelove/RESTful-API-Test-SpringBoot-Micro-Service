package com.ross.restfulcrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描所有的mapper接口
@MapperScan("com.ross.restfulcrud.mapper")
public class RestfulCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulCrudApplication.class, args);
    }

}
