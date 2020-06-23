package org.sang.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {
        return fileUpload(uploadFile, request);
    }

    @PostMapping("/uploads")
    public String uploads(MultipartFile[] uploadFile, HttpServletRequest request) {
        String result = "";
        for (MultipartFile i : uploadFile){
            result = fileUpload(i, request);
        }
        return result;
    }

    private String fileUpload(MultipartFile uploadFile, HttpServletRequest request) {
        /* 保存上传文件的目录 */
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        /* 在保存文件目录下准备创建日期相关文件夹，以日期归档 */
        File folder = new File(realPath + format);
        if (!folder.isDirectory())
            folder.mkdirs();
        /* 原始文件名 */
        String oldName = uploadFile.getOriginalFilename();
        /* 新文件名 */
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());

        try {
            /* 文件保存 */
            uploadFile.transferTo(new File(folder, newName));
            /* 返回文件访问路径 */
            String filePath = request.getScheme()
                    + "://" + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    +"/public/uploadFile/"+ format + newName;
            return filePath;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败";
    }
}
