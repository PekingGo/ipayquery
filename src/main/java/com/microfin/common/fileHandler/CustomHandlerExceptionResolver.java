package com.microfin.common.fileHandler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.microfin.common.util.C;
import com.microfin.common.util.L;
import com.microfin.logic.controller.FileHandlerController;

/**
 * SpringMVC异常处理
 *
 * @author guanxp 2015/3/2
 */
public class CustomHandlerExceptionResolver extends DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * log4j日志
     */
    private static Logger logger = Logger.getLogger(CustomHandlerExceptionResolver.class.getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {

        // System.out.println("==============异常开始=============");
        logger.error("==============Start=============");
        // ex.printStackTrace();
        // 打印异常log
        logger.error(ex.getClass(), ex);
        // System.out.println("==============异常结束=============");
        // 异常结束
        logger.error("==============End=============");

        if (ex instanceof MultipartException) {
            try {
                response.setContentType(C.CONTENT_TYPE_HTML_UTF_8);
                PrintWriter out = response.getWriter();
                out.println(String.format(FileHandlerController.getUploadScript(), false, L.RESOLVE_MULTIPART_EXCEPTION, "[]"));
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            ModelAndView mv = new ModelAndView("common/error");
            mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
            return mv;
        }
    }
}
