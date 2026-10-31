package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.StudentMark;

/**
 * 标记管理Service接口
 *
 * @author xzr
 * @date 2024-08-18
 */
public interface IStudentMarkService
{
    /**
     * 查询标记管理
     *
     * @param markId 标记管理主键
     * @return 标记管理
     */
    public StudentMark selectStudentMarkByMarkId(Long markId);

    /**
     * 查询标记管理列表
     *
     * @param studentMark 标记管理
     * @return 标记管理集合
     */
    public List<StudentMark> selectStudentMarkList(StudentMark studentMark);

    /**
     * 新增标记管理
     *
     * @param studentMark 标记管理
     * @return 结果
     */
    public int insertStudentMark(StudentMark studentMark);

    /**
     * 修改标记管理
     *
     * @param studentMark 标记管理
     * @return 结果
     */
    public int updateStudentMark(StudentMark studentMark);

    /**
     * 批量删除标记管理
     *
     * @param markIds 需要删除的标记管理主键集合
     * @return 结果
     */
    public int deleteStudentMarkByMarkIds(Long[] markIds);

    /**
     * 删除标记管理信息
     *
     * @param markId 标记管理主键
     * @return 结果
     */
    public int deleteStudentMarkByMarkId(Long markId);


}
