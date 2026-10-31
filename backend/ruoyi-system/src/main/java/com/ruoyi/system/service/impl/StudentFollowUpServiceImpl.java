package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.StudentMark;
import com.ruoyi.system.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentFollowUpMapper;
import com.ruoyi.system.domain.StudentFollowUp;
import com.ruoyi.system.service.IStudentFollowUpService;

/**
 * 跟进记录Service业务层处理
 *
 * @author xzr
 * @date 2024-08-18
 */
@org.springframework.transaction.annotation.Transactional
@Service
public class StudentFollowUpServiceImpl implements IStudentFollowUpService
{
    @Autowired
    private StudentFollowUpMapper studentFollowUpMapper;
    @Autowired
    private StudentMapper studentMapper;


    /**
     * 查询跟进记录
     *
     * @param followId 跟进记录主键
     * @return 跟进记录
     */
    @Override
    public StudentFollowUp selectStudentFollowUpByFollowId(Long followId)
    {
        return studentFollowUpMapper.selectStudentFollowUpByFollowId(followId);
    }

    /**
     * 查询跟进记录列表
     *
     * @param studentFollowUp 跟进记录
     * @return 跟进记录
     */
    @Override
    public List<StudentFollowUp> selectStudentFollowUpList(StudentFollowUp studentFollowUp)
    {
        return studentFollowUpMapper.selectStudentFollowUpList(studentFollowUp);
    }

    /**
     * 新增跟进记录
     *
     * @param studentFollowUp 跟进记录
     * @return 结果
     */
    @Override
    public int insertStudentFollowUp(StudentFollowUp studentFollowUp)
    {
        studentFollowUp.setCreateTime(DateUtils.getNowDate());
        studentFollowUpMapper.insertStudentFollowUp(studentFollowUp);
        Student student = studentMapper.selectStudentByStudentId(studentFollowUp.getStudentId());
        StudentFollowUp studentFollowUp1= new StudentFollowUp();
        studentFollowUp1.setStudentId(student.getStudentId());
        List<StudentFollowUp> studentFollowUps = studentFollowUpMapper.selectStudentFollowUpList(studentFollowUp1);
        if (studentFollowUps.size()>0){
            student.setFollow(1L);
        }else{
            student.setFollow(2L);
        }
        return studentMapper.updateStudent(student);
    }

    /**
     * 修改跟进记录
     *
     * @param studentFollowUp 跟进记录
     * @return 结果
     */
    @Override
    public int updateStudentFollowUp(StudentFollowUp studentFollowUp)
    {
        studentFollowUp.setUpdateTime(DateUtils.getNowDate());
        return studentFollowUpMapper.updateStudentFollowUp(studentFollowUp);
    }

    /**
     * 批量删除跟进记录
     *
     * @param followIds 需要删除的跟进记录主键
     * @return 结果
     */
    @Override
    public int deleteStudentFollowUpByFollowIds(Long[] followIds)
    {
        java.util.Set<Long> students=new java.util.HashSet<>();
        for(Long id:followIds) {StudentFollowUp item=studentFollowUpMapper.selectStudentFollowUpByFollowId(id); if(item!=null) students.add(item.getStudentId());}
        int count=studentFollowUpMapper.deleteStudentFollowUpByFollowIds(followIds);
        for(Long id:students) {
            StudentFollowUp query=new StudentFollowUp(); query.setStudentId(id);
            Student student=studentMapper.selectStudentByStudentId(id);
            student.setFollow(studentFollowUpMapper.selectStudentFollowUpList(query).isEmpty()?2L:1L);
            studentMapper.updateStudent(student);
        }
        return count;
    }

    /**
     * 删除跟进记录信息
     *
     * @param followId 跟进记录主键
     * @return 结果
     */
    @Override
    public int deleteStudentFollowUpByFollowId(Long followId)
    {
        StudentFollowUp studentFollowUp = studentFollowUpMapper.selectStudentFollowUpByFollowId(followId);
        studentFollowUpMapper.deleteStudentFollowUpByFollowId(followId);
        Student student = studentMapper.selectStudentByStudentId(studentFollowUp.getStudentId());
        if (student.getMarkList().size()>0){
            student.setFollow(1L);
        }else{
            student.setFollow(2L);
        }
        return studentMapper.updateStudent(student);
    }
}
