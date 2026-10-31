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
import com.ruoyi.system.domain.StudentFollowUp;
import com.ruoyi.system.service.IStudentFollowUpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 跟进记录Controller
 *
 * @author xzr
 * @date 2024-08-18
 */
@RestController
@RequestMapping("/system/follow_up")
public class StudentFollowUpController extends BaseController
{
    @Autowired
    private IStudentFollowUpService studentFollowUpService;

    /**
     * 查询跟进记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentFollowUp studentFollowUp)
    {
        startPage();
        List<StudentFollowUp> list = studentFollowUpService.selectStudentFollowUpList(studentFollowUp);
        return getDataTable(list);
    }

    /**
     * 导出跟进记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:export')")
    @Log(title = "跟进记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentFollowUp studentFollowUp)
    {
        List<StudentFollowUp> list = studentFollowUpService.selectStudentFollowUpList(studentFollowUp);
        ExcelUtil<StudentFollowUp> util = new ExcelUtil<StudentFollowUp>(StudentFollowUp.class);
        util.exportExcel(response, list, "跟进记录数据");
    }

    /**
     * 获取跟进记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:query')")
    @GetMapping(value = "/{followId}")
    public AjaxResult getInfo(@PathVariable("followId") Long followId)
    {
        return success(studentFollowUpService.selectStudentFollowUpByFollowId(followId));
    }

    /**
     * 新增跟进记录
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:add')")
    @Log(title = "跟进记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentFollowUp studentFollowUp)
    {
        return toAjax(studentFollowUpService.insertStudentFollowUp(studentFollowUp));
    }

    /**
     * 修改跟进记录
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:edit')")
    @Log(title = "跟进记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentFollowUp studentFollowUp)
    {
        return toAjax(studentFollowUpService.updateStudentFollowUp(studentFollowUp));
    }

    /**
     * 删除跟进记录
     */
    @PreAuthorize("@ss.hasPermi('system:follow_up:remove')")
    @Log(title = "跟进记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{followIds}")
    public AjaxResult remove(@PathVariable Long[] followIds)
    {
        return toAjax(studentFollowUpService.deleteStudentFollowUpByFollowIds(followIds));
    }
}
