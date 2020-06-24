package org.sang.resolver;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义 Error 页面
 *
 * 自定义 MyErrorViewResolver 实现 ErrorViewResolver 复写 resolverErrorView 方法
 */

@Component
public class MyErrorViewResolver implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("message", "我在 MyErrorViewResolver 中定义了自定义 Error 页面，所以跑到这里来了");
        mv.addAllObjects(model);
        return mv;
    }
}
