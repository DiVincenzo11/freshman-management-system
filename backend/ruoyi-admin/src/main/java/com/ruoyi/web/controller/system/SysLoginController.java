package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.system.domain.SysRoleChildMenu;
import com.ruoyi.system.mapper.SysRoleChildMenuMapper;
import com.ruoyi.system.service.ISysRoleChildMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysRoleChildMenuService childMenuService;


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        //子权限集合
        Set<String> select=new HashSet<>();
        Set<String> edit=new HashSet<>();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        for (int i = 0; i < user.getRoles().size(); i++) {
            SysRoleChildMenu sysRoleChildMenu=new SysRoleChildMenu();
            sysRoleChildMenu.setRoleId(user.getRoles().get(i).getRoleId());
            sysRoleChildMenu.setChildMenuType(1L);
            List<SysRoleChildMenu> sysRoleChildMenus = childMenuService.selectSysRoleChildMenuList(sysRoleChildMenu);
            System.out.println("sysROle"+sysRoleChildMenu);
            for (int j = 0; j < sysRoleChildMenus.size(); j++) {
                select.add(sysRoleChildMenus.get(j).getChildMenu().getChildMenuName());
            }
            sysRoleChildMenu.setChildMenuType(2L);
            sysRoleChildMenus = childMenuService.selectSysRoleChildMenuList(sysRoleChildMenu);
            for (int j = 0; j < sysRoleChildMenus.size(); j++) {
                edit.add(sysRoleChildMenus.get(j).getChildMenu().getChildMenuName());
            }
        }
        ajax.put("select",select);
        ajax.put("edit",edit);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
