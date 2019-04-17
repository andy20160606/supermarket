package cn.youguang.util;

import java.io.*;
import java.util.Date;

/**
 * Created by Andy丶 on 2017/3/8.
 * 主要对数据库进行备份操作
 */
public class DBUtil {

    private static  Long M_S = null;
    /**
     *
     *
     * @param exec     mysqldump 命令位置  例子:e:\MySQL\bin\mysqldump -h localhost -P3306 -uroot -p123 db_name
     * @param targetFile   目标文件目录以及文件名 例子： d:\\bbs.sql  若为null  默认存盘 d:\\ + 20160301(根据时间来填充此字段) + .sql
     *  @param h   多少小时备份一次 一般选择24
     */
    public static void backup(String exec,String targetFile,Long h) {

        M_S = h * 3600000;

        try {
            Runtime rt = Runtime.getRuntime();

            // 调用 调用mysql的安装目录的命令
            Process child = rt
                    .exec(exec);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = null;
            if(targetFile == null) {
                fout = new FileOutputStream("d:\\backup\\" + DateUtil.getDateString(new Date()) + ".sql");
            } else {
                fout = new FileOutputStream(targetFile);
            }

            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();

            System.out.println("数据库已备份" +new Date());

            try {
                Thread.sleep(M_S);
                backup(exec, targetFile, h);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
