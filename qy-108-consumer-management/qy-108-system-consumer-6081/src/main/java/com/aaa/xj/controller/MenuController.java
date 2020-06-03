package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Menu;
import com.aaa.xj.service.IQYService;
import com.aaa.xj.vo.MenuVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "菜单管理" ,tags = "菜单管理接口")
public class MenuController  extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Author:  xj
     * @description
     *      条件查询
     * @Data: 2020/6/3
     * @param [map]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("selectMenuByField")
    public ResultData selectMenuByField(@RequestBody Map map){
            List<Menu> menus = iqyService.selectMenuByField(map);
            if (null !=menus){
                return super.getSuccess(menus);
            }
            return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据id查询菜单信息
     * @Data: 2020/6/3
     * @param [menuId]
     * @Return:com.aaa.xj.base.ResultData
     */
    @GetMapping("selectMenuByPrimaryKey")
    public ResultData selectMenuByPrimaryKey(Long menuId){
        Menu menu = iqyService.selectMenuByPrimaryKey(menuId);
        if (null !=menu){
            return super.getSuccess(menu);
        }
        return super.getFalse();
    }
    /**
     * @Author:  xj
     * @description
     *      遍历查询所有菜单信息
     * @Data: 2020/6/3
     * @param [parentId]
     * @Return:java.util.List<com.aaa.xj.vo.MenuVo>
     */
    @GetMapping("selectMenuByParentId")
    public ResultData selectMenuByParentId(Object parentId){
        List<MenuVo> menuVos = iqyService.selectMenuByParentId(parentId);
        if (menuVos.size()>0){
            return super.getSuccess(menuVos);
        }
        return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      新增菜单
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("insertMenu")
    public ResultData insertMenu(Menu menu){
        Boolean aBoolean = iqyService.insertMenu(menu);
        if (aBoolean){
            return super.addSuccess();
        }
        return super.addFalse();
    }

    /**
     * @Author:  xj
     * @description\
     *      新增按钮
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("insertMenuButton")
    public ResultData insertMenuButton(Menu menu){
        Boolean aBoolean = iqyService.insertMenuButton(menu);
        if (aBoolean){
            return super.addSuccess();
        }
        return super.addFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据id批量删除菜单
     * @Data: 2020/6/3
     * @param [menuIds]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("deleteMenuByMenuId")
    public ResultData deleteMenuByMenuId(@RequestBody List<Object> menuIds){
        Boolean aBoolean = iqyService.deleteMenuByMenuId(menuIds);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据id修改菜单信息
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Integer
     */
    @PostMapping("updateMenuByPrimaryKey")
    public ResultData updateMenuByPrimaryKey( Menu menu){
        Integer integer = iqyService.updateMenuByPrimaryKey(menu);
        if (null !=integer){
            return super.updateSuccess();
        }
        return super.updateFalse();
    }
}
