package cn.youguang.entity;

/**
 * 此处填写该项目用到的所有常量
 * Created by Andy丶 on 2017/3/26.
 */
public class Constant {
    public static int USER_MAN = 1;  ////用户-性别男
    public static int USER_WOMAN = 0;  ////用户-性别女
    public static int USER_GLY = 1;    //管理员
    public static int USER_XS = 2;     // 销售
    public static int USER_GCS = 3;  //工程师
    public static int ZCLX_SQ = 1;  //支持类型-售前
    public static int ZCLX_SH = 2;   //支持类型-售后
    public static int ZCLX_PX = 3;  ////支持类型-培训
    public static int KDZT_KQ = 1;  ////开单状态-开启
    public static int KDZT_GB = 0;  ////开单状态-开启
     public static String FILE_FJ_DIR = "D:\\XMGL\\FJ";  //附件的目录位置 ,在这里不要存储在tomcat的目录中，以免重新部署项目，带来麻烦 windows
    // public static String FILE_FJ_DIR = "/root/fj";  //附件的目录位置 ,在这里不要存储在tomcat的目录中，以免重新部署项目，带来麻烦

}
