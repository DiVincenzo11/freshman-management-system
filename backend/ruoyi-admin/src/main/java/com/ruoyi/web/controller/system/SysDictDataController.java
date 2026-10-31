package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.ChildMenu;
import com.ruoyi.system.service.IChildMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private IChildMenuService childMenuService;

    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode)
    {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return success(data);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @PreAuthorize("@ss.hasPermi('system:dict_mark:query')")
    @GetMapping(value = "/studentMark")
    public TableDataInfo dictTypeByStudentMark(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }
    /**
     * 根据字典类型查询字典数据信息
     */
    @PreAuthorize("@ss.hasPermi('system:dict_grades:query')")
    @GetMapping(value = "/studentGrades")
    public TableDataInfo dictTypeByStudentGrades(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }
    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_mark:add')")
    @PostMapping("/studentMark")
    public AjaxResult addStudentMark(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }
    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_grades:add')")
    @PostMapping("/studentGrades")
    public AjaxResult addGrades(@Validated @RequestBody SysDictData dict)
    {
        ChildMenu childMenu=new ChildMenu();
        childMenu.setChildMenuClass("Grades");
        childMenu.setChildMenuName(dict.getDictValue());
        childMenu.setOrderNum(99L);
        childMenu.setChildMenuType(1L);
        childMenu.setChildMenuLabel(dict.getDictLabel());
        childMenuService.insertChildMenu(childMenu);
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_mark:edit')")
    @PutMapping("/studentMark")
    public AjaxResult editByStudentMark(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_grades:edit')")
    @PutMapping("/studentGrades")
    public AjaxResult editByStudentGrades(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }
    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_mark:remove')")
    @DeleteMapping("/studentMark/{dictCodes}")
    public AjaxResult removeByStudentMark(@PathVariable Long[] dictCodes)
    {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict_grades:remove')")
    @DeleteMapping("/studentGrades/{dictCodes}")
    public AjaxResult removeByStudentGrades(@PathVariable Long[] dictCodes)
    {
        ChildMenu childMenu=new ChildMenu();
        for (int i = 0; i < dictCodes.length; i++) {
            SysDictData sysDictData = dictDataService.selectDictDataById(dictCodes[i]);
            childMenu.setChildMenuLabel(sysDictData.getDictLabel());
            List<ChildMenu> childMenus = childMenuService.selectChildMenuList(childMenu);
            for (int j = 0; j < childMenus.size(); j++) {
                childMenuService.deleteChildMenuByChildMenuId(childMenus.get(j).getChildMenuId());
            }
        }
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }

}
