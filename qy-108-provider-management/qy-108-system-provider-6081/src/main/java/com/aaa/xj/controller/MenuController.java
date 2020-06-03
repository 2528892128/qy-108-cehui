package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.mapper.MenuMapper;
import com.aaa.xj.model.Menu;
import com.aaa.xj.service.MenuService;
import com.aaa.xj.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * @Author:  xj
     * @description
     *      根据条件查看菜单信息
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.util.List<com.aaa.xj.model.Menu>
     */
    @PostMapping("selectMenuByField")
    public List<Menu> selectMenuByField(@RequestBody Map map){
        //判断前端是否传值成功
        if (!"".equals(map) && null !=map){
            try {
                List<Menu> menus = menuService.selectMenuByField(map);
                if (!"".equals(menus) && null !=menus){
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
     *     根据id查询菜单信息
     * @Data: 2020/6/3
     * @param [menuId]
     * @Return:com.aaa.xj.model.Menu
     */
    @GetMapping("selectMenuByPrimaryKey")
    public Menu selectMenuByPrimaryKey(@RequestParam("menuId") Long menuId){

        try {
            //执行查询方法
            Menu menu = menuService.selectMenuByPrimaryKey(menuId);
            if (null !=menu){
                return menu;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      遍历查询所有菜单
     * @Data: 2020/6/3
     * @param [parentId]
     * @Return:java.util.List<com.aaa.xj.vo.MenuVo>
     */
    @GetMapping("selectMenuByParentId")
    public  List<MenuVo> selectMenuByParentId(@RequestParam("parentId") Object parentId){

        try {
            //调用查询方法
            List<MenuVo> menuVos = menuService.selectMenuByParentId(parentId);
            //判断是否查询成功
            if (null != menuVos){
                return menuVos;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
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
    @PostMapping("insertMenu")
    public Boolean insertMenu(@RequestBody Menu menu){
        try {
            Boolean aBoolean = menuService.insertMenu(menu);
            if (aBoolean){
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
     *      新增按钮
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Boolean
     */
    @PostMapping("insertMenuButton")
    public Boolean insertMenuButton(@RequestBody Menu menu){

        try {
            //调用service的新增方法
            Boolean aBoolean = menuService.insertMenuButton(menu);
            //判断是否新增成功
            if (aBoolean){
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
     *      根据id批量删除
     * @Data: 2020/6/3
     * @param [menuIds]
     * @Return:java.lang.Boolean
     */
    @PostMapping("deleteMenuByMenuId")
    public Boolean deleteMenuByMenuId(@RequestBody List<Object> menuIds){
        try {
            //调用批量删除的方法
            Boolean aBoolean = menuService.deleteMenuByMenuId(menuIds);
            if (aBoolean){
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
     *      根据主键id 进行菜单数据的更新
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Integer
     */
    @PostMapping("updateMenuByPrimaryKey")
    public Integer updateMenuByPrimaryKey(@RequestBody Menu menu){
        Integer updateResult = null;
        try {
            //调用修改方法
            updateResult = menuService.updateMenuByPrimaryKey(menu);
            if (updateResult > 0 ){
                return updateResult;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
