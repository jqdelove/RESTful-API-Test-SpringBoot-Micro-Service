package com.ross.restfulcrud.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//在容器中加入自定义的ErrorAttributes，能够达到显示想要的错误消息的效果
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","ross");

        //获取放在request中的自定义的错误信息
        Map ext = (Map) webRequest.getAttribute("ext", 0);
        //将自定义错误消息放入map
        map.put("ext",ext);
        return map;
    }
}
