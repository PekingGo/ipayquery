package com.pansoft.logic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pansoft.common.AjaxResponse;
import com.pansoft.common.fileHandler.FileInfo;
import com.pansoft.common.fileHandler.FileUploadProgressListener;
import com.pansoft.common.fileHandler.FileUtil;
import com.pansoft.common.fileHandler.Progress;
import com.pansoft.common.util.C;
import com.pansoft.common.util.DateUtil;
import com.pansoft.common.util.L;
import com.pansoft.common.util.StringUtil;

/**
 * 文件处理控制器类
 *
 * @author guanxp 2015/02/28
 */
@Controller
@RequestMapping("/file")
public class FileHandlerController {

    // 上传结果脚本模板
    private static String UPLOAD_SCRIPT = "<script type='text/javascript'>parent.$.FILE.uploadResult(%b, '%s', %s);</script>";
    // 下载异常结果脚本模板
    private static String DOWNLOAD_ERR_SCRIPT = "<script type='text/javascript'>parent.$.FILE.downloadError('%s');</script>";

    /**
     * 获取上传结果脚本模板
     *
     * @return
     */
    public static String getUploadScript() {
        return UPLOAD_SCRIPT;
    }

    /**
     * 以比特流方式上传文件
     *
     * @param files
     * @param folder
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/uploadByBytes", method = RequestMethod.POST)
    public String uploadByBytes(@RequestParam(value = "file", required = true) CommonsMultipartFile[] files,
            @RequestParam(value = "folder", required = true) String folder, HttpServletRequest request, HttpServletResponse response) {

        // 打印输出流
        PrintWriter out = null;
        // 文件输出流
        FileOutputStream fos = null;
        // 文件输入流
        InputStream is = null;

        try {
            response.setContentType(C.CONTENT_TYPE_HTML_UTF_8);
            out = response.getWriter();

            // 检查文件是否存在
            if (files == null || files.length == 0) {
                out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FILE_NOT_FOUND, "[]"));
                return null;
            }

            // 检查目录名是否为空
            if (folder == null || folder.trim().length() == 0) {
                out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FOLDER_ISNULL, "[]"));
                return null;
            }

            // 检查文件的大小
            for (int i = 0; i < files.length; i++) {
                // 上传文件不能为空
                if (files[i].isEmpty()) {
                    String message = String.format(L.UPLOAD_FILE_SIZE_ZERO, files[i].getOriginalFilename());
                    out.println(String.format(UPLOAD_SCRIPT, false, message, "[]"));
                    return null;
                }
                // 上传文件不能超过系统设置的最大上传值
                if (files[i].getSize() > C.MAX_UPLOAD_SIZE) {
                    String message = String.format(L.UPLOAD_FILE_SIZE_EXCEED, files[i].getOriginalFilename());
                    out.println(String.format(UPLOAD_SCRIPT, false, message, "[]"));
                    return null;
                }
            }

            List<FileInfo> fileArray = new ArrayList<FileInfo>();

            // 循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {

                String folderPath = FileUtil.getServerAbsolutePath(request) + "/" + folder;
                String fileNameNoEx = DateUtil.dateToString(C.YYYYMMDDHHMMSSSSS_24);
                String fileEx = FileUtil.getFileExtension(files[i].getOriginalFilename());
                String fileName = (fileEx.length() == 0) ? fileNameNoEx : (fileNameNoEx + "." + fileEx);
                String filePath = folderPath + "/" + fileName;

                // 初始化文件夹
                File folderFile = new File(folderPath);
                if (!folderFile.exists()) {
                    if (!folderFile.mkdirs()) {
                        continue;
                    }
                }

                long start = System.currentTimeMillis();

                // 取得输出流，同时重命名上传的文件
                fos = new FileOutputStream(filePath);
                // 取得上传文件的输入流
                is = files[i].getInputStream();

                // 以写字节的方式写文件
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.flush();
                fos.close();
                is.close();

                System.out.println(String.format(L.UPLOAD_FILE_TIME_USED, fileName, (System.currentTimeMillis() - start)));

                // 上传文件信息
                FileInfo fi = new FileInfo();
                fi.setFileNameNoEx(fileNameNoEx);
                fi.setFileExtensionName(fileEx);
                fi.setFileName(fileName);
                fi.setFileSize(FileUtil.getFileSize(filePath));
                fi.setFilePath(folder + "/" + fileName + "1--" + files[i].getName() + "2--" + files[i].getOriginalFilename());
                fileArray.add(fi);
            }

            // 将文件上传后的信息返回给客户端
            JSON json = JSONSerializer.toJSON(fileArray);
            System.out.println(String.format(L.RESPONSE_JSON_DATA, json.toString()));

            out.println(String.format(UPLOAD_SCRIPT, true, L.UPLOAD_FILE_SUCCESS, json.toString()));

        } catch (Exception e) {
            e.printStackTrace();
            out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FILE_EXCEPTION, "[]"));
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 以SpringMVC解析器方式上传
     *
     * @param files
     * @param folder
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/uploadByResovler", method = RequestMethod.POST)
    public String uploadByResovler(@RequestParam(value = "file", required = true) CommonsMultipartFile[] files,
            @RequestParam(value = "folder", required = true) String folder, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        try {
            response.setContentType(C.CONTENT_TYPE_HTML_UTF_8);
            out = response.getWriter();

            // 检查文件是否存在
            if (files == null || files.length == 0) {
                out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FILE_NOT_FOUND, "[]"));
                return null;
            }

            // 检查目录名是否为空
            if (folder == null || folder.trim().length() == 0) {
                out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FOLDER_ISNULL, "[]"));
                return null;
            }

            // 检查文件的大小
            for (int i = 0; i < files.length; i++) {
                // 上传文件不能为空
                if (files[i].isEmpty()) {
                    String message = String.format(L.UPLOAD_FILE_SIZE_ZERO, files[i].getOriginalFilename());
                    out.println(String.format(UPLOAD_SCRIPT, false, message, "[]"));
                    return null;
                }
                // 上传文件不能超过系统设置的最大上传值
                if (files[i].getSize() > C.MAX_UPLOAD_SIZE) {
                    String message = String.format(L.UPLOAD_FILE_SIZE_EXCEED, files[i].getOriginalFilename());
                    out.println(String.format(UPLOAD_SCRIPT, false, message, "[]"));
                    return null;
                }
            }

            List<FileInfo> fileArray = new ArrayList<FileInfo>();

            // 循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {

                String folderPath = FileUtil.getServerAbsolutePath(request) + "/" + folder;
                String fileNameNoEx = DateUtil.dateToString(C.YYYYMMDDHHMMSSSSS_24);
                String fileEx = FileUtil.getFileExtension(files[i].getOriginalFilename());
                String fileName = (fileEx.length() == 0) ? fileNameNoEx : (fileNameNoEx + "." + fileEx);
                String filePath = folderPath + "/" + fileName;
                // 取得原始文件名
                String fileOriginalName = files[i].getOriginalFilename();
                if (StringUtil.notEmpty(fileOriginalName)) {
                    String[] strs = fileOriginalName.split("\\.");
                    fileOriginalName = strs[0];
                }

                // 初始化文件夹
                File folderFile = new File(folderPath);
                if (!folderFile.exists()) {
                    if (!folderFile.mkdirs()) {
                        continue;
                    }
                }

                long start = System.currentTimeMillis();

                // 转存文件
                files[i].transferTo(new File(filePath));

                System.out.println(String.format(L.UPLOAD_FILE_TIME_USED, fileName, (System.currentTimeMillis() - start)));

                // 上传文件信息
                FileInfo fi = new FileInfo();
                fi.setFileNameNoEx(fileNameNoEx);
                fi.setFileExtensionName(fileEx);
                fi.setFileName(fileName);
                fi.setFileSize(FileUtil.getFileSize(filePath));
                fi.setFilePath(folder + "/" + fileName);
                fi.setFileOriginalName(fileOriginalName);
                fileArray.add(fi);
            }

            // 将文件上传后的信息返回给客户端
            JSON json = JSONSerializer.toJSON(fileArray);
            System.out.println(String.format(L.RESPONSE_JSON_DATA, json.toString()));

            out.println(String.format(UPLOAD_SCRIPT, true, L.UPLOAD_FILE_SUCCESS, json.toString()));

        } catch (Exception e) {
            e.printStackTrace();
            out.println(String.format(UPLOAD_SCRIPT, false, L.UPLOAD_FILE_EXCEPTION, "[]"));
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

        return null;
    }

    /**
     * 删除目录或者文件（支持级联删除）
     *
     * @param path
     * @return
     */
    private boolean deleteFile(String path) {

        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!deleteFile(files[i].getAbsolutePath())) {
                        return false;
                    }
                }
            }
            return file.delete();
        }
        return false;
    }

    /**
     * 获取上传进度
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadProgress", method = RequestMethod.POST)
    public @ResponseBody AjaxResponse<Progress> uploadProgress(HttpServletRequest request) {

        AjaxResponse<Progress> res = new AjaxResponse<Progress>();

        HttpSession session = request.getSession(true);

        FileUploadProgressListener uploadProgressListener = (FileUploadProgressListener) session.getAttribute(C.UPLOAD_PROGRESS_LISTENER_KEY);
        if (uploadProgressListener == null) {
            res.setMessage(L.UPLOAD_FILE_LISTENER_ISNULL);
            res.setSuccess(false);
            return res;
        }

        Progress progress = new Progress();
        progress.setp100KsRead(uploadProgressListener.getp100KsRead());
        progress.setpBytesRead(uploadProgressListener.getpBytesRead());
        progress.setpContentLength(uploadProgressListener.getpContentLength());
        progress.setPercentDone(uploadProgressListener.getPercentDone());
        progress.setpItems(uploadProgressListener.getpItems());

        System.out.println(String.format(L.UPLOAD_FILE_PROGRESS, progress.getPercentDone()));

        List<Progress> list = new ArrayList<Progress>();
        list.add(progress);
        res.setDataList(list);
        res.setSuccess(true);

        return res;
    }

    /**
     * 以比特流方式下载文件
     *
     * @param _filePath
     * @param _fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadByBytes", method = RequestMethod.POST)
    public String downloadByBytes(@RequestParam(value = "filePath", required = true) String _filePath,
            @RequestParam(value = "fileName", required = true) String _fileName, HttpServletRequest request, HttpServletResponse response) {

        // 打印输出流
        PrintWriter out = null;
        // 文件输出流
        OutputStream os = null;
        // 文件输入流
        FileInputStream fis = null;

        try {
            response.setCharacterEncoding(C.ENCODE_UTF_8);

            // 检查文件下载路径是否为空
            if (_filePath == null || _filePath.trim().length() == 0) {
                // 设置下载失败头信息
                out = response.getWriter();
                response.addHeader("Content-Type", C.CONTENT_TYPE_HTML_UTF_8);
                out.println(String.format(DOWNLOAD_ERR_SCRIPT, L.DOWNLOAD_FILE_PATH_ISNULL));
                return null;
            }

            // 下载文件路径
            String filePath = FileUtil.getServerAbsolutePath(request) + "/" + _filePath;
            // 下载后保存的文件名
            String fileName = _fileName;
            // 如果保存的文件名为空，则用filePath中的文件名代替
            if (_fileName == null || _fileName.trim().length() == 0) {
                fileName = FileUtil.getFileName(filePath);
            }

            // 判断下载文件是否存在
            if (FileUtil.getFileSize(filePath) == -1) {
                // 设置下载失败头信息
                out = response.getWriter();
                response.addHeader("Content-Type", C.CONTENT_TYPE_HTML_UTF_8);
                out.println(String.format(DOWNLOAD_ERR_SCRIPT, L.DOWNLOAD_FILE_NOT_FOUND));
                return null;
            }

            long start = System.currentTimeMillis();

            // 获取文件输出流
            os = response.getOutputStream();
            // 获取文件输入流
            fis = new FileInputStream(filePath);

            // 设置下载文件头信息
            response.addHeader("Content-Type", C.CONTENT_TYPE_STREAM_UTF_8);
            String fileName_ISO88591 = new String(fileName.getBytes(C.ENCODE_UTF_8), C.ENCODE_ISO_88591);
            response.addHeader("Content-Disposition", String.format(C.CONTENT_DISPOSITION, fileName_ISO88591));
            response.addHeader("Content-Length", String.valueOf(FileUtil.getFileSize(filePath)));

            // 以读字节的方式读文件
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();

            System.out.println(String.format(L.DOWNLOAD_FILE_TIME_USED, fileName, (System.currentTimeMillis() - start)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 删除（多个）文件
     *
     * @param _filePaths
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody AjaxResponse delete(@RequestParam(value = "filePaths", required = true) List<String> _filePaths, HttpServletRequest request) {

        AjaxResponse res = new AjaxResponse();

        // 检查要删除的文件信息是否为空
        if (_filePaths == null || _filePaths.size() == 0) {
            res.setMessage(L.DELETE_FILE_INFO_NOT_FOUND);
            res.setSuccess(false);
            return res;
        }

        // 检查要删除的文件是否存在
        for (int i = 0; i < _filePaths.size(); i++) {
            String _filePath = _filePaths.get(i);
            // 下载文件路径
            String filePath = FileUtil.getServerAbsolutePath(request) + "/" + _filePath;
            if (FileUtil.getFileSize(filePath) == -1) {
                res.setMessage(String.format(L.DELETE_FILE_NOT_FOUND, FileUtil.getFileName(filePath)));
                res.setSuccess(true);
                return res;
            }
        }

        long start = System.currentTimeMillis();

        // 删除文件
        for (int i = 0; i < _filePaths.size(); i++) {
            String _filePath = _filePaths.get(i);
            // 下载文件路径
            String filePath = FileUtil.getServerAbsolutePath(request) + "/" + _filePath;
            // 删除文件
            if (!deleteFile(filePath)) {
                res.setMessage(String.format(L.DELETE_FILE_FAILURE, FileUtil.getFileName(filePath)));
                res.setSuccess(false);
                return res;
            }
        }

        System.out.println(String.format(L.DELETE_FILE_TIME_USED, (System.currentTimeMillis() - start)));

        // 设置删除成功头信息
        res.setMessage(String.format(L.DELETE_FILE_SUCCESS));
        res.setSuccess(true);
        return res;
    }
}
