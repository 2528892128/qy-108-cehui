package com.aaa.xj.base;

import com.aaa.xj.utils.Map2BeanUtils;
import com.aaa.xj.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {

    private Class<T> cache = null; // 本地缓存池

    @Autowired
    private Mapper<T> mapper;

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *        上面的这个Mapper为了保证安全性，必须要是private类型，那么他的子类(UserService)就调用不到
     *          所以需要提供方法给子类用
     *@Data: 2020/5/12 10:10
     *@Return:tk.mybatis.mapper.common.Mapper
     */
    protected Mapper getMapper(){
        return mapper;
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *      新增
     *@Data: 2020/5/12 10:14
     *@Return:java.lang.Integer
     */

    public Integer add(T t) throws Exception{
        return mapper.insertSelective(t);
    }


    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  通过主键删除
     *@Data: 2020/5/12 10:14
     *@Return:java.lang.Integer
     */
    public Integer delete(T t) throws Exception {
        return mapper.deleteByPrimaryKey(t);
    }


    /**
     *@Summary:
     *@Author:  xj
     *@description
     *   通过主键批量删除
     *            能用java代码来搞定的东西，千万不要上子查询
     *           阿里巴巴代码规约手册:
     *               如果联查的时候超过两张表，那么你一定要把这个联查拆开，放在java代码中实现
     *@Data: 2020/5/12 10:30
     *@Return:java.lang.Integer
     */
    public Integer batchDelete(List<Object> ids) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @Author:  xj
     * @description
     * 重载批量删除方法
     * @Data: 2020/6/2
     * @param [roleId]
     * @Return:java.lang.Integer
     */
    public Integer batchDelete1(List<Object> roleIds) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("roleId", roleIds)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @Author:  xj
     * @description
     * @Data: 2020/6/3
     * @param [menuIds]
     * @Return:java.lang.Integer
     */
    public Integer batchDeleteMenu(List<Object> menuIds) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("menuId", menuIds)).build();
        return mapper.deleteByExample(example);
    }
    /**
     *@Summary:
     *@Author:  xj
     *@description
     *       更新功能
     *@Data: 2020/5/12 10:33
     *@Return:java.lang.Integer
     */
    public Integer update(T t) throws Exception {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *
     * 批量更新
     *          t:所要更新的数据(t代表实体类，只能存放一个id)
     *          ids:通用主键来进行更新
     *@Data: 2020/5/12 10:38
     *@Return:java.lang.Integer
     */
    public Integer batchUpdate(T t, Object[] ids) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t, example);
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *      查询一条数据
     *@Data: 2020/5/12 10:39
     *@Return:T
     */
    public T queryOne(T t) throws Exception {
        return mapper.selectOne(t);
    }


    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  通过指定字段查询一条数据
     *            (有些表中有唯一键(username, iphone_num...))
     *@Data: 2020/5/12 10:44
     *@Return:T
     */
    public T queryByField(Sqls where, String orderByField, String... fields) throws Exception {
        return (T) queryByFieldsBase(null, null, where, orderByField, fields).get(0);
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  条件查询集合
     *@Data: 2020/5/12 10:50
     *@Return:java.util.List<T>
     */
    public List<T> queryListByFields(Sqls where, String orderByField, String... fields) throws Exception {
        return queryByFieldsBase(null, null, where, orderByField, fields);
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  条件查询分页
     *@Data: 2020/5/12 11:12
     *@Return:com.github.pagehelper.PageInfo<T>
     */
    public PageInfo<T> queryListByPageAndFields(Integer pageNo, Integer pageSize, Sqls where, String orderByFileds, String... fields) throws Exception {
        return new PageInfo<T>(queryByFieldsBase(pageNo, pageSize, where, orderByFileds, fields));
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     * 条件查询
     *@Data: 2020/5/12 11:30
     *@Return:java.util.List<T>
     */
    public List<T> queryList(T t) throws Exception {
        return mapper.select(t);
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  分页查询
     *@Data: 2020/5/12 11:35
     *@Return:com.github.pagehelper.PageInfo<T>
     */
    public PageInfo<T> queryListByPage(T t, Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }


    /**
     *@Summary:
     *@Author:  xj
     *@description
     * 根据反射获取实例对象
     *@Data: 2020/5/13 10:47
     *@Return:T
     */
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArguement());
    }

    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  封装条件查询，分页查询以及排序查询的通用方法(多条件查询)
     *@Data: 2020/5/13 10:35
     *@Return:java.util.List<T>
     */
    private List<T> queryByFieldsBase(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... field) {
        Example.Builder builder = null;
        if(null == field || field.length == 0) {
            // 没有条件查询，说明查询的是所有数据
            builder = Example.builder(getTypeArguement());
        } else {
            // 指定某些/某个字段进行查询
            builder = Example.builder(getTypeArguement())
                    .select(field);
        }
        if(null != where) {
            builder = builder.where(where);
        }

        if(null != orderByField) {
            builder = builder.orderByDesc(orderByField);
        }

        Example example = builder.build();

        if(null != pageNo && null != pageSize) {
            // pageHelper通用分页插件
            PageHelper.startPage(pageNo, pageSize);
        }

        List list = getMapper().selectByExample(example);
        return list;
    }
    /**
     *@Summary:
     *@Author:  xj
     *@description
     *      获取子类泛型类型
     *@Data: 2020/5/13 10:30
     *@Return:java.lang.Class<T>
     */
    private Class<T> getTypeArguement() {
        if(null == cache) {
            cache = (Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        }
        return cache;
    }
    
    /**
     *@Summary:
     *@Author:  xj
     *@description
     *  获取String容器
     *            TODO 咱们用不到，有待补充
     *@Data: 2020/5/13 10:54
     *@Return:org.springframework.context.ApplicationContext
     */

    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }


}
