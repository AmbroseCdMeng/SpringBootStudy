package org.sang.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器。实现 HandlerInterceptor 接口
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor >>>>> preHandler");
        return true;
    }

    /* 按照顺序依次执行， Controller 在 preHandler 之后，当且仅当 preHandler 返回 true 时，后面的才会继续 */

    /* 当拦截器链内有多个拦截器时，postHandler 在拦截器链内所有拦截器返回成功时才会调用，而 afterCompletion 只有 preHandler 返回 true 才调用 */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor >>>>> postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor >>>>> afterCompletion");
    }
}
