package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.MenuMapper;
import com.aaa.xj.model.Menu;
import com.aaa.xj.utils.DateUtils;
import com.aaa.xj.vo.MenuVo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.MacArabic;

import java.util.List;
import java.util.Map;

import static com.aaa.xj.staticstatus.MenuTyoeProperties.*;

@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * @Author:  xj
     * @description
     *      根据条件查询菜单信息
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.util.List<com.aaa.xj.model.Menu>
     */
    public List<Menu> selectMenuByField(Map map){
        //判断前段是否传值成功
        if (!"".equals(map) && null !=map){
            try {
                //调用条件查询方法
                List<Menu> menus = menuMapper.selectMenuByField(map);
                //判断是否查询成功
                if (menus.size()>0 && null !=menus){
                    return menus;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      根据主键id查询具体菜单数据
     * @Data: 2020/6/3
     * @param [menuId]
     * @Return:com.aaa.xj.model.Menu
     */
    public Menu selectMenuByPrimaryKey(Long menuId){
        //判断menuId是否传递成功
        if (null !=menuId){
            //执行查询
            Menu menu = menuMapper.selectByPrimaryKey(menuId);
            //判断是否查询出值
            if (null !=menu){
                return menu;
            }
            return null;
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *     遍历查询所有菜单
     * @Data: 2020/6/3
     * @param [parentId]
     * @Return:java.util.List<com.aaa.xj.vo.MenuVo>
     */
    public List<MenuVo> selectMenuByParentId(Object parentId){
        //第一次查询传入的id为0 则为查询所有的菜单表
        List<MenuVo> menuVos = menuMapper.selectMenuByParentId(parentId);
        if (null != menuVos && menuVos.size() > 0){
            //循环遍历第一次查询的集合
            for (MenuVo menuVO : menuVos) {
                //以本身的MenuId为参数  进行查询本身的子菜单
                Object id1 = menuVO.getMenuId();
                //循环查询 直到本身菜单不在存在子菜单
                List<MenuVo> menuVos1 = this.selectMenuByParentId(id1);
                //添加到父级菜单的集合中 进行数据的返回
                menuVO.setChildrenList(menuVos1);
            }
            return menuVos;
        }
        return null;
    }


    /**
     * @Author:  xj
     * @description
     *      新增菜单
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Boolean
     */
    public Boolean insertMenu(Menu menu){
        //获取当前时间 放入menu中
        menu.setCreateTime(DateUtils.getCurrentDate())
                //将菜单默认type值0传入
                .setType(MENU_TYPE_CAIDAN);

        try {
            //调用新增方法
            int insert =  menuMapper.insert(menu);
            //判断受影响的行数
            if (insert>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     *          新增按钮
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Boolean
     */
    public Boolean insertMenuButton(Menu menu){
        //将时间和按钮默认类型传入
        menu.setCreateTime(DateUtils.getCurrentDate())
                .setType(MENU_TYPE_ANNIU);
        try {
            //执行新增方法
            int insert = menuMapper.insert(menu);
            if (insert>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     *      根据主键id批量删除菜单数据
     * @Data: 2020/6/3
     * @param [menuIds]
     * @Return:java.lang.Boolean
     */
    public Boolean deleteMenuByMenuId(List<Object> menuIds){

        if (!"".equals(menuIds) && null !=menuIds){
            try {
                //调用父类重载的批量删除方法
                Integer integer = super.batchDeleteMenu(menuIds);
                if (integer>0){
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * @Author:  xj
     * @description
     * 根据主键id 更新菜单信息
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Integer
     */
    public Integer updateMenuByPrimaryKey(Menu menu){
        if (null != menu){
            //更新时传入当前时间 更新数据
            menu.setModifyTime(DateUtils.getCurrentDate())
                    .setCreateTime(DateUtils.getCurrentDate());

            int updateResult = menuMapper.updateByPrimaryKey(menu);
            if (updateResult  > 0){
                return updateResult;
            }
        }
        return null;
    }

}
