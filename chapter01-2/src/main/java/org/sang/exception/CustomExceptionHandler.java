package org.sang.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义全局异常
 */
//@ControllerAdvice     //因优先级高于自定义错误页面，故屏蔽以测试自定义错误页面
public class CustomExceptionHandler {

    /**
     * 上传文件大小超出异常处理
     * @param e
     * @param resp
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView uploadException(Exception e, HttpServletResponse resp) throws IOException{
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("文件大小超出限制");
//        out.flush();
//        out.close();
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
