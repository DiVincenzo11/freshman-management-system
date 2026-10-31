package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentMarkMapper;
import com.ruoyi.system.domain.StudentMark;
import com.ruoyi.system.service.IStudentMarkService;

/**
 * 标记管理Service业务层处理
 *
 * @author xzr
 * @date 2024-08-18
 */
@org.springframework.transaction.annotation.Transactional
@Service
public class StudentMarkServiceImpl implements IStudentMarkService
{
    @Autowired
    private StudentMarkMapper studentMarkMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 查询标记管理
     *
     * @param markId 标记管理主键
     * @return 标记管理
     */
    @Override
    public StudentMark selectStudentMarkByMarkId(Long markId)
    {
        return studentMarkMapper.selectStudentMarkByMarkId(markId);
    }

    /**
     * 查询标记管理列表
     *
     * @param studentMark 标记管理
     * @return 标记管理
     */
    @Override
    public List<StudentMark> selectStudentMarkList(StudentMark studentMark)
    {
        return studentMarkMapper.selectStudentMarkList(studentMark);
    }

    /**
     * 新增标记管理
     *
     * @param studentMark 标记管理
     * @return 结果
     */
    private void refresh(Long studentId) {
        Student student=studentMapper.selectStudentByStudentId(studentId);
        StudentMark query=new StudentMark(); query.setStudentId(studentId);
        List<StudentMark> marks=studentMarkMapper.selectStudentMarkList(query);
        List<SysDictData> dicts=dictDataMapper.selectDictDataByType("student_mark");
        String labels=marks.stream().map(m -> dicts.stream().filter(d -> d.getDictValue().equals(String.valueOf(m.getMarkContent()))).map(SysDictData::getDictLabel).findFirst().orElse("")).distinct().collect(java.util.stream.Collectors.joining("、"));
        student.setIsmark(labels); studentMapper.updateStudent(student);
    }
    @Override public int insertStudentMark(StudentMark item) {
        item.setCreateTime(DateUtils.getNowDate());
        int count=studentMarkMapper.insertStudentMark(item); refresh(item.getStudentId()); return count;
    }
    @Override public int updateStudentMark(StudentMark item) {
        StudentMark old=studentMarkMapper.selectStudentMarkByMarkId(item.getMarkId());
        item.setUpdateTime(DateUtils.getNowDate());
        int count=studentMarkMapper.updateStudentMark(item); refresh(old.getStudentId());
        if(item.getStudentId()!=null) refresh(item.getStudentId()); return count;
    }
    @Override public int deleteStudentMarkByMarkIds(Long[] ids) {
        java.util.Set<Long> students=new java.util.HashSet<>();
        for(Long id:ids) {StudentMark m=studentMarkMapper.selectStudentMarkByMarkId(id); if(m!=null) students.add(m.getStudentId());}
        int count=studentMarkMapper.deleteStudentMarkByMarkIds(ids);
        for(Long id:students) refresh(id); return count;
    }
    @Override public int deleteStudentMarkByMarkId(Long id) { return deleteStudentMarkByMarkIds(new Long[]{id}); }
}
