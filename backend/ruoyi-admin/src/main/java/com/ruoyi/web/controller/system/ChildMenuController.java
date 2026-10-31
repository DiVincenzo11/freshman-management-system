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
import com.ruoyi.system.domain.ChildMenu;
import com.ruoyi.system.service.IChildMenuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 子菜单管理Controller
 *
 * @author xzr
 * @date 2024-08-19
 */
@RestController
@RequestMapping("/system/child_menu")
public class ChildMenuController extends BaseController
{
    @Autowired
    private IChildMenuService childMenuService;

    /**
     * 查询子菜单管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ChildMenu childMenu)
    {
        startPage();
        List<ChildMenu> list = childMenuService.selectChildMenuList(childMenu);
        return getDataTable(list);
    }

    /**
     * 导出子菜单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:child_menu:export')")
    @Log(title = "子菜单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChildMenu childMenu)
    {
        List<ChildMenu> list = childMenuService.selectChildMenuList(childMenu);
        ExcelUtil<ChildMenu> util = new ExcelUtil<ChildMenu>(ChildMenu.class);
        util.exportExcel(response, list, "子菜单管理数据");
    }

    /**
     * 获取子菜单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:child_menu:query')")
    @GetMapping(value = "/{childMenuId}")
    public AjaxResult getInfo(@PathVariable("childMenuId") Long childMenuId)
    {
        return success(childMenuService.selectChildMenuByChildMenuId(childMenuId));
    }

    /**
     * 新增子菜单管理
     */
    @PreAuthorize("@ss.hasPermi('system:child_menu:add')")
    @Log(title = "子菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChildMenu childMenu)
    {
        return toAjax(childMenuService.insertChildMenu(childMenu));
    }

    /**
     * 修改子菜单管理
     */
    @PreAuthorize("@ss.hasPermi('system:child_menu:edit')")
    @Log(title = "子菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChildMenu childMenu)
    {
        return toAjax(childMenuService.updateChildMenu(childMenu));
    }

    /**
     * 删除子菜单管理
     */
    @PreAuthorize("@ss.hasPermi('system:child_menu:remove')")
    @Log(title = "子菜单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{childMenuIds}")
    public AjaxResult remove(@PathVariable Long[] childMenuIds)
    {
        return toAjax(childMenuService.deleteChildMenuByChildMenuIds(childMenuIds));
    }
}
