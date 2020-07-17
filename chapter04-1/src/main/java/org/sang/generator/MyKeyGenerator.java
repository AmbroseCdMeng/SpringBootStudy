package org.sang.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 自定义缓存 key 的生成器
 */
@Component
public class MyKeyGenerator implements KeyGenerator {
    /**
     *
     * @param target 当前对象
     * @param method 当前请求方法
     * @param params 当前参数
     * @return 生成的 key
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return Arrays.toString(params);
    }
}
