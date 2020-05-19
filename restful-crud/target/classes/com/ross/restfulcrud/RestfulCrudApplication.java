package com.ross.restfulcrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//扫描所有的mapper接口
@MapperScan("com.ross.restfulcrud.mapper")
@EnableCaching
public class RestfulCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulCrudApplication.class, args);
    }

}
