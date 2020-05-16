package com.ross.restfulcrud.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义LocaleResolver，默认是根据浏览器的请求头判断语言信息
 * 现在点击页面按钮手动选择语言信息
 */
public class CustomLocaleResolver implements LocaleResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomLocaleResolver.class);

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        Locale l = Locale.getDefault();
//        logger.info("get default locale.");
        if (!StringUtils.isEmpty(locale)){
            String[] s = locale.split("_");
            l = new Locale(s[0],s[1]);
//            logger.info("get custom locale.");
        }
        return l;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
