package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.PrincipalMapper;
import com.aaa.xj.model.Principal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-20-2020/5/20 14:38
 */
@Service
public class PrincipalService extends BaseService<Principal> {

    @Autowired
    private PrincipalMapper principalMapper;

    /**
     * @Description: 获取负责人的信息
     * @Param: [userId]
     * @return: java.lang.String
     * @Author: ygy
     * @Date: 2020/5/20 14:50
     */
    public List<Principal> qureyOne(Long userId){
        //获取信息
        List<Principal> principal = principalMapper.qureyOne(userId);
        //判断负责人的信息是否为空
        if (null != principal){
            //不为空就返回信息
            return principal;
        }
        return null;
    }

    /**
     * @Description: 根据userid 分页查询负责人信息
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/31 9:36
     */
    public PageInfo selectPrincipalByPage(Long userId,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        try {
            //根据userId查询负责人
            List<Principal> principals = principalMapper.qureyOne(userId);
            //判断查询的结果是否为空
            if (null != principals && !"".equals(principals)){
                //不为空就把查询结果进行分页
                PageInfo<Principal> principalPageInfo = new PageInfo<>(principals);
                //返回分页后的数据
                return principalPageInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @Description: 根据userId删除负责人信息
     * @Param: [userId]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/30 21:39
     */
    public Integer deletePrincipal(Long userId){
        if (null != userId && !"".equals(userId)){
            Integer integer = principalMapper.deletePrincipal(userId);
            //判断删除受影响的行数
            if (integer > 0){
                //大于0说明删除成功返回受影响的行数
                return integer;
            }else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * @Description: 根据主键id查看负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/5/31 20:53
     */
    public List<Principal> selectOnePrincipal(Long id){
        List<Principal> principals = principalMapper.selectOnePrincipal(id);
        //判断根据主键id查询到的数据是否为空
        if (null != principals && !"".equals(principals)){
            //不为空说明查询成功返回查询结果
            return principals;
        }
        return null;
    }

    /**
     * @Description: 新增负责人信息
     * @Param: [principal, uploadService]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 10:17
     */
//    public Integer insertPrincipal(Principal principal, MultipartFile multipartFile){
//        //获取时间
//        Date date = new Date();
//        //设置时间格式作为创建时间
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        String format = simpleDateFormat.format(date);
//        // 获取系统当前时间的毫秒数
//        Long timeMillis = System.currentTimeMillis();
//        // 创建Random对象
//        Random random = new Random();
//        // 做一个随机数，随机区间是0-9999之间随机
//        Integer randomNum = random.nextInt(9999);
//        //把系统当前时间和randomNum相加作为id
//        Long id = randomNum + timeMillis;
//        //把生成的时间和id传进去
//        Principal principal1 = principal.setCreateTime(format)
//                .setId(id);
//        //new 一个UploadService
//        UploadService uploadService = new UploadService();
//        //把文件传进去进行上传返回一个boolean类型
//        Boolean upload = uploadService.upload(multipartFile);
//        //接受前台要增加的负责人信息进行添加
//        int insert = principalMapper.insert(principal1);
//        //判断增加受影响的行数
//        if (insert > 0) {
//            //大于0 说明成功返回受影响的行数
//            return insert;
//        }
//        return 0;
//    }

    /**
     * @Description: 查询要修改的信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/6/1 17:08
     */
    public List<Principal> selectUpdatePrincipal(Long id){
        List<Principal> principals = principalMapper.selectOnePrincipal(id);
        //判断查询的信息是否为空
        if (null != principals && !"".equals(principals)){
            //不为空返回查询信息
            return principals;
        }
        return null;
    }


    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: int
     * @Author: ygy
     * @Date: 2020/5/21 19:21
     */
//    public Integer updateList(Principal principal,MultipartFile multipartFile){
//        //获取时间
//        Date date = new Date();
//        //设置时间格式作为修改时间
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        String format = simpleDateFormat.format(date);
//        //new 一个UploadService
//        UploadService uploadService = new UploadService();
//        //把文件传进去进行上传返回一个boolean类型
//        Boolean upload = uploadService.upload(multipartFile);
//
//        Principal principal1 = principal.setCreateTime(format);
//        //判断负责人信息是否为空
//        if (null != principal1){
//            //不为空就修改
//            Integer integer = principalMapper.updatePrincipal(principal1);
//            //判断是否修改成功
//            if (integer>0){
//                return integer;
//            }else {
//                return 0;
//            }
//        }
//        return 0;
//    }

}
