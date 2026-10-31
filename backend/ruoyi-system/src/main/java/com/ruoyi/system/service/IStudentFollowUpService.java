package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StudentFollowUp;

/**
 * 跟进记录Service接口
 * 
 * @author xzr
 * @date 2024-08-18
 */
public interface IStudentFollowUpService 
{
    /**
     * 查询跟进记录
     * 
     * @param followId 跟进记录主键
     * @return 跟进记录
     */
    public StudentFollowUp selectStudentFollowUpByFollowId(Long followId);

    /**
     * 查询跟进记录列表
     * 
     * @param studentFollowUp 跟进记录
     * @return 跟进记录集合
     */
    public List<StudentFollowUp> selectStudentFollowUpList(StudentFollowUp studentFollowUp);

    /**
     * 新增跟进记录
     * 
     * @param studentFollowUp 跟进记录
     * @return 结果
     */
    public int insertStudentFollowUp(StudentFollowUp studentFollowUp);

    /**
     * 修改跟进记录
     * 
     * @param studentFollowUp 跟进记录
     * @return 结果
     */
    public int updateStudentFollowUp(StudentFollowUp studentFollowUp);

    /**
     * 批量删除跟进记录
     * 
     * @param followIds 需要删除的跟进记录主键集合
     * @return 结果
     */
    public int deleteStudentFollowUpByFollowIds(Long[] followIds);

    /**
     * 删除跟进记录信息
     * 
     * @param followId 跟进记录主键
     * @return 结果
     */
    public int deleteStudentFollowUpByFollowId(Long followId);
}
