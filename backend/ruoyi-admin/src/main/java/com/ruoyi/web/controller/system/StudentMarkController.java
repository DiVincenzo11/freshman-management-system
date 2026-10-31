package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.Student;
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
import com.ruoyi.system.domain.StudentMark;
import com.ruoyi.system.service.IStudentMarkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标记管理Controller
 *
 * @author xzr
 * @date 2024-08-18
 */
@RestController
@RequestMapping("/system/mark")
public class StudentMarkController extends BaseController
{
    @Autowired
    private IStudentMarkService studentMarkService;

    /**
     * 查询标记管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:mark:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentMark studentMark)
    {
        startPage();
        List<StudentMark> list = studentMarkService.selectStudentMarkList(studentMark);
        return getDataTable(list);
    }

    /**
     * 导出标记管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:mark:export')")
    @Log(title = "标记管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentMark studentMark)
    {
        List<StudentMark> list = studentMarkService.selectStudentMarkList(studentMark);
        ExcelUtil<StudentMark> util = new ExcelUtil<StudentMark>(StudentMark.class);
        util.exportExcel(response, list, "标记管理数据");
    }

    /**
     * 获取标记管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mark:query')")
    @GetMapping(value = "/{markId}")
    public AjaxResult getInfo(@PathVariable("markId") Long markId)
    {
        return success(studentMarkService.selectStudentMarkByMarkId(markId));
    }

    /**
     * 新增标记管理
     */
    @PreAuthorize("@ss.hasPermi('system:mark:add')")
    @Log(title = "标记管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentMark studentMark)
    {
        List<StudentMark> studentMarks = studentMarkService.selectStudentMarkList(studentMark);
        if (studentMarks.size()>0){
            return error("相同的标记只能添加一次");
        }
        int i = studentMarkService.insertStudentMark(studentMark);
        JSONObject jsonObject=new JSONObject();
        return toAjax1(i,studentMark);
    }

    /**
     * 修改标记管理
     */
    @PreAuthorize("@ss.hasPermi('system:mark:edit')")
    @Log(title = "标记管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentMark studentMark)
    {
        return toAjax(studentMarkService.updateStudentMark(studentMark));
    }

    /**
     * 删除标记管理
     */
    @PreAuthorize("@ss.hasPermi('system:mark:remove')")
    @Log(title = "标记管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{markIds}")
    public AjaxResult remove(@PathVariable Long[] markIds)
    {
        return toAjax(studentMarkService.deleteStudentMarkByMarkIds(markIds));
    }


}
