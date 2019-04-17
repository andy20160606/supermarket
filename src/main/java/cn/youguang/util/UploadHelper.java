package cn.youguang.util;

import cn.youguang.entity.Constant;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author xdwang
 * @create 2012-11-19 下午6:24:03
 * @email:xdwangiflytek@gmail.com
 * @description 上传帮助类
 */
public class UploadHelper {

    /**
     * @param request
     * @return MultipartFile集合
     * @descrption 根据HttpServletRequest对象获取MultipartFile集合
     * @author xdwang
     * @create 2012-11-19下午5:11:41
     */
    public static MultiValueMap<String, MultipartFile> getFileSet(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartRequest.getMultiFileMap();
    }

    /**
     * @param file MultipartFile对象
     * @return 保存的全路径 如“D:\\File\\2345678.txt”
     * @throws Exception 文件保存失败
     * @descrption 保存文件
     * @author xdwang
     * @create 2012-11-19下午4:17:36
     */




    public static String uploadFile(MultipartFile file)
            throws Exception {
        String path = "D:\\XMGL\\FJ";
//        path += File.separator + OrderUtil.getOrderNo() + JavaFile.getExtensionName(file.getOriginalFilename());
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        String lastFileName = UUID.randomUUID().toString() + extName;
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File temp = new File(path);
        if (!temp.isDirectory()) {
            temp.mkdir();
        }
        String fileFullPath = path + lastFileName;
        File f = new File(fileFullPath);
        FileCopyUtils.copy(file.getBytes(), f);
        return fileFullPath;
    }

    /**
     * @param file         MultipartFile对象
     * @param maxLength    文件最大限制
     * @param allowExtName 不允许上传的文件扩展名
     * @return 文件格式是否合法
     * @descrption 验证文件格式，这里主要验证后缀名
     * @author xdwang
     * @create 2012-11-19下午4:08:12
     */
    private static boolean validateFile(MultipartFile file, long maxLength,
                                        String[] allowExtName) {
        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();

        // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename == "") {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }
}