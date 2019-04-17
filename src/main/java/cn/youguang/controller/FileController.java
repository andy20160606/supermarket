package cn.youguang.controller;

import cn.youguang.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-15 14:04
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${file.UploadDir}")
    private String filePath;



    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        Result result  = new Result();
        if (file.isEmpty()) {
            result.setMsg("file is empty ");
            return result;
        }

        String fileName = file.getOriginalFilename();

        String extName = file.getOriginalFilename().substring(fileName.lastIndexOf("."))
                .toLowerCase();
        String finalFileName = UUID.randomUUID().toString() + extName;

        File dest = new File(filePath + finalFileName);

        try {
            file.transferTo(dest);
            result.setMsg("上传成功");
            result.setObj(finalFileName);
            result.setSuccess(true);
            LOGGER.info("上传成功");
            return result;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }





    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam String fileName) {
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath +fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
            //    response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
















//    @GetMapping("/multiUpload")
//    public String multiUpload() {
//        return "multiUpload";
//    }
//
//    @PostMapping("/multiUpload")
//    @ResponseBody
//    public String multiUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//
//        for (int i = 0; i < files.size(); i++) {
//            MultipartFile file = files.get(i);
//            if (file.isEmpty()) {
//                return "上传第" + (i++) + "个文件失败";
//            }
//            String fileName = file.getOriginalFilename();
//
//            File dest = new File(filePath + fileName);
//            try {
//                file.transferTo(dest);
//                LOGGER.info("第" + (i + 1) + "个文件上传成功");
//            } catch (IOException e) {
//                LOGGER.error(e.toString(), e);
//                return "上传第" + (i++) + "个文件失败";
//            }
//        }
//
//        return "上传成功";

//    }
}
