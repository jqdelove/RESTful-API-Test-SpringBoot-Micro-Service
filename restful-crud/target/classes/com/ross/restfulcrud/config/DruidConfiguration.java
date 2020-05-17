package com.ross.restfulcrud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    //使properties文件中设置的数据源属性生效，前缀为spring.datasource的属性
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置druid的监控（druid的特色）
    //1、配置管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //设置请求的路径为/druid/*
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map map = new HashMap();
        map.put("loginUsername","admin");
        map.put("loginPassword","123456");
        map.put("allow","");//允许所有人访问
        bean.setInitParameters(map);
        return bean;
    }

    //2、配置监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
        Map map = new HashMap();
        //需要排除的内容
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);

        //设置filter拦截的请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
