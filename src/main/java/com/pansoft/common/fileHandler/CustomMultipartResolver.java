package com.pansoft.common.fileHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.pansoft.common.util.C;

/**
 * 文件上传分解器
 * @author guanxp
 * 2015/2/28
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {
    
    public CustomMultipartResolver() {
        super();
    }

    public CustomMultipartResolver(ServletContext servletContext) {
        super(servletContext);
    }
    
    @Override
    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
        
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory); 
        upload.setSizeMax(-1); 
        if (request != null) {
            // 注入监听
            FileUploadProgressListener uploadProgressListener = new FileUploadProgressListener();
            upload.setProgressListener(uploadProgressListener);
            request.getSession().setAttribute(C.UPLOAD_PROGRESS_LISTENER_KEY, uploadProgressListener);
        }
        return upload;
    }
    
    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
        // 获取到request，要用到session
        this.request = request;
        return super.resolveMultipart(request);
    }
    
    private HttpServletRequest request;
}
