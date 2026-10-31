package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Student;

/**
 * 学生管理Service接口
 *
 * @author xzr
 * @date 2024-08-18
 */
public interface IStudentService
{
    /**
     * 查询学生管理
     *
     * @param studentId 学生管理主键
     * @return 学生管理
     */
    public Student selectStudentByStudentId(Long studentId);

    /**
     * 查询学生管理列表
     *
     * @param student 学生管理
     * @return 学生管理集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生管理
     *
     * @param studentIds 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteStudentByStudentIds(Long[] studentIds);

    /**
     * 删除学生管理信息
     *
     * @param studentId 学生管理主键
     * @return 结果
     */
    public int deleteStudentByStudentId(Long studentId);

    public String importUser(List<Student> studentList);

    public List<Student> selectDormitory(Student student);
}
