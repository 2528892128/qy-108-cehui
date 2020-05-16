package com.aaa.xj.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 *          这个工具类就是文件上传的通用工具类
 *          按照咱们架构的原则，config就依赖了common
 *          这个时候假设把FTPUtils放到了common项目中，那么也就意味着所有的有关于ftp的jar包
 *          都必须要出现在common项目中，假设如果consumer项目使用到了common，那最终consumer
 *          也会把ftp的jar包引入到项目中，从而增大了项目的负担，无法实现职责单一化！！
 */
public class FTPUtils {

    private FTPUtils() {

    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
            ftp文件上传工具类
     *      如果要实现文件上传，就必须要先连接上ftp服务器
     *      然后就是要进行登录到ftp中
     *      然后就是需要开始准备上传了--->就必须要知道用户的上传路径
     *      (我前天规定:咱们就以当天的日期为文件夹实现图片的存放)
     *          eg:2020/05/15
     *      虽然文件的上传地址已经知道了，但是还不知道具体ftp的根目录地址(/home/ftp)
     *      还需要知道文件的名称
     * @Data: 2020/5/16 8:58
     * @Return:java.lang.Boolean
     */
    public static Boolean uploadFile(String host, Integer port, String username, String password,
                                     String filePath, String basePath, String fileName, InputStream input){
        //1.创建临时路径(方便于后面我需要上传文件的时候，检测日期文件夹是否存在，如果不存在需要进行创建)
        // 2020-->2020/05--->2020/05/15(就是方便于拼接这个文件夹目录)
        String tmpPath = "";
        // 2.创建FTPClient对象(这也就是Ftp给java所提供的API，可以实现连接ftp，登录ftp，创建文件夹，实现文件上传和下载..)
        FTPClient ftp = new FTPClient();
        try {
            // 3.连接ftp服务器
            ftp.connect(host, port);
            // 4.执行登录操作
            // 这一步在java代码中永远不会进入cache
            ftp.login(username, password);
            // 5.验证服务器是否已经连接和登录成功(如果成功，则返回230，如果失败，则返回530/503)
            int reply = ftp.getReplyCode();
            // 6.根据返回的状态码来进行判断用户是否已经登录和连接成功
            // isPositiveCompletion():就是检测状态码的信息，如果成功返回true，如果失败返回false
            if(!FTPReply.isPositiveCompletion(reply)) {
                // 说明账号和密码错误(虽然已经知道账号密码错误，但是并不知道是否连接成功，如果连接成功了，需要及时断开连接(释放资源))
                ftp.disconnect();// 如果在这释放资源失败了
                return false;
            }
            // 如果没有进入if，就说明已经连接和登录成功了
            // 7.那么需要开始判断目标上传路径是否存在(/home/ftp/2020/05/15)
            // basePath + filePath---> /home/ftp/2020/05/15
            // changeWorkingDirectory():判断路径是否存在，如果存在返回true，如果不存在则返回false
            if(!ftp.changeWorkingDirectory(basePath + filePath)) {
                // 说明路径不存在，需要进行创建文件夹
                // java中可是没有mkdir -p命令的 mkdir -p /home/ftp/2020/05/15/，只能一层一层创建
                // 8.分割filePath--->String[] ---> ["", "2020", "05", "15"]
                String[] dirs = filePath.split("/");
                // 9.把basePath(/home/ftp)赋值给临时路径(tmpPath)
                tmpPath = basePath;
                // 10.循环dirs数组
                for(String dir : dirs) {
                    // 严谨判断 判断dir一定不能为null
                    // 跳过本次循环，进入下一次循环
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    // 11.拼接临时路径
                    // /home/ftp/2020
                    tmpPath += "/" + dir;
                    // 12.再次检测确保该路径不存在
                    if(!ftp.changeWorkingDirectory(tmpPath)) {
                        // 13.创建文件夹
                        // makeDirectory()--->就是创建文件夹的方法，返回为bolean
                        if(!ftp.makeDirectory(tmpPath)) {
                            return false;
                        } else {
                            System.out.println(ftp.changeWorkingDirectory(tmpPath));
                        }
                    }
                }
            }

            // 14.如果if没有进入，就说明这些文件夹都已经存在了，可以直接进行上传
            // FTP是支持IO的，但是IO的性能很低，所以可以使用二进制字符流的形式进行上传
            // 把文件转换为二进制字符流的模式来进行上传
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // 15.这才是真正实现文件上传的方法
            // storeFile():就是文件上传的方法，返回true/false
            if(!ftp.storeFile(fileName, input)) {
                return false;
            }
            // 16.关闭输入流
            input.close();
            // 17.退出ftp
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
