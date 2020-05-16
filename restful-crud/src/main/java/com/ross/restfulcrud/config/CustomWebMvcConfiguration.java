package com.ross.restfulcrud.config;

import com.ross.restfulcrud.component.CustomLocaleResolver;
import com.ross.restfulcrud.component.LoginHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义WebMvc的配置，以前是编写xml配置文件，现在通过实现接口自定义配置
 */
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(CustomWebMvcConfiguration.class);

    /**
     * 注册WebMvcConfigurer组件
     * 类似于springMVC配置文件中设置viewController，以及注册自定义的拦截器都在这一设置
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //类似于springMVC配置文件中的设置viewController，采用thymeleaf模板引擎的自动查找templates文件夹下的html页面
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/dashboard.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //注册登录检查拦截器
                //拦截所有路径下的任意请求：/**
                //exclude表示排除的请求，如果不登录，登录页面也进不去，所以需要排除
                //SpringBoot已经做好了静态资源映射，因此不用担心静态资源无法访问的情况
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/","/employee/login");
            }
        };


    }

    /**
     * 将自定义的LocaleResolver注册到配置中生效
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        logger.info("customLocaleResolver success.");
        return new CustomLocaleResolver();
    }

}
