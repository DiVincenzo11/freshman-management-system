package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StudentMark;

/**
 * 标记管理Mapper接口
 * 
 * @author xzr
 * @date 2024-08-18
 */
public interface StudentMarkMapper 
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
     * 删除标记管理
     * 
     * @param markId 标记管理主键
     * @return 结果
     */
    public int deleteStudentMarkByMarkId(Long markId);

    /**
     * 批量删除标记管理
     * 
     * @param markIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentMarkByMarkIds(Long[] markIds);
}
