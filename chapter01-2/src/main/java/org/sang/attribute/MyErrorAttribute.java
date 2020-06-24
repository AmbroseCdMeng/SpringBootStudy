package org.sang.attribute;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义 Error 数据
 *
 * 自定义 MyErrorAttribute 继承 DefaultErrorAttribute 复写 getErrorAttributes 方法
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("message", "我在 MyErrorAttribute 中定义了自定义 Error 页面，所以跑到这里来了");
        errorAttributes.remove("error");
        return errorAttributes;
    }
}
