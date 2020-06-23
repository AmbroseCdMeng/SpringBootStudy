package org.sang.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice 和 @ModelAttribute 配置全局数据
 */

@ControllerAdvice
public class GlobalConfig {
    // value 属性表示该条数据的 key， 返回值为该条数据的 value
    // 此时，在任意 Controller 下都可以通过参数中的 Model 获取到 info 的数据。在 HelloController 中示例
    @ModelAttribute(value = "info")
    public Map<String, String> userinfo(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "AmbroseCdMeng");
        map.put("gender","男");
        return map;
    }

    /**
     * 请求参数预处理
     *
     * 如此，在每个方法中给出相应的一个 Field 的前缀之后，就变成了 a.name=Ambrose&b.name=CdMeng
     * @param binder
     */
    @InitBinder("b")  //表示该方法是处理 @ModelAttribute("b") 对应的参数
    public void init1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
        //这里不仅仅可以设置前缀，还可以设置允许的字段、禁用字段、必填字段以及各种验证器等等。
    }
    @InitBinder("a")  //表示该方法是处理 @ModelAttribute("a") 对应的参数
    public void init2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }
}
