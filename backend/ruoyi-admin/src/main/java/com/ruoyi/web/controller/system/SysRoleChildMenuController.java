package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysRoleChildMenu;
import com.ruoyi.system.service.ISysRoleChildMenuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 中间表Controller
 *
 * @author xzr
 * @date 2024-08-19
 */
@RestController
@RequestMapping("/system/sys_role_child_menu")
public class SysRoleChildMenuController extends BaseController
{
    @Autowired
    private ISysRoleChildMenuService sysRoleChildMenuService;

    /**
     * 查询中间表列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysRoleChildMenu sysRoleChildMenu)
    {
        startPage();
        List<SysRoleChildMenu> list = sysRoleChildMenuService.selectSysRoleChildMenuList(sysRoleChildMenu);
        return getDataTable(list);
    }

    /**
     * 导出中间表列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoleChildMenu sysRoleChildMenu)
    {
        List<SysRoleChildMenu> list = sysRoleChildMenuService.selectSysRoleChildMenuList(sysRoleChildMenu);
        ExcelUtil<SysRoleChildMenu> util = new ExcelUtil<SysRoleChildMenu>(SysRoleChildMenu.class);
        util.exportExcel(response, list, "中间表数据");
    }

    /**
     * 获取中间表详细信息
     */
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return success(sysRoleChildMenuService.selectSysRoleChildMenuByRoleId(roleId));
    }

    /**
     * 新增中间表
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysRoleChildMenu sysRoleChildMenu)
    {
        return toAjax(sysRoleChildMenuService.insertSysRoleChildMenu(sysRoleChildMenu));
    }

    /**
     * 修改中间表
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysRoleChildMenu sysRoleChildMenu)
    {
        return toAjax(sysRoleChildMenuService.updateSysRoleChildMenu(sysRoleChildMenu));
    }

    /**
     * 删除中间表
     */
	@DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(sysRoleChildMenuService.deleteSysRoleChildMenuByRoleIds(roleIds));
    }
}
