package com.aaa.xj.service;

import com.aaa.xj.properties.FTPProperties;
import com.aaa.xj.utils.DateUtils;
import com.aaa.xj.utils.FTPUtils;
import com.aaa.xj.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


@Service
public class UploadService {

    @Autowired
    private FTPProperties ftpProperties;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *       ftp文件上传
     * @Data: 2020/5/30
     * @param [file]
     * @Return:java.lang.Boolean
     */
    public Boolean upload(MultipartFile file) {
        // 1.获取原始文件的名称(获取目的就是为了获取文件的后缀名)
        String oldFileName = file.getOriginalFilename();
        // 2.获取新的文件名(不带后缀)
        String newFileName = FileNameUtils.getFileName();
        // 3.获取到最终的文件名(新的带后缀的文件名)
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf("."));
        try {
            // 4.获取今天日期格式化后的数据(yyyy-MM-dd--->yyyy/MM/dd)
            String filePath = DateUtils.formatDate(new Date(), "yyyy/MM/dd");
            return FTPUtils.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
