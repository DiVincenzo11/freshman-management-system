package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标记管理对象 student_mark
 * 
 * @author xzr
 * @date 2024-08-18
 */
public class StudentMark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long markId;

    /** 标记内容 */
    @Excel(name = "标记内容")
    private Long markContent;

    /** 被标记的学生ID */
    @Excel(name = "被标记的学生ID")
    private Long studentId;

    public void setMarkId(Long markId) 
    {
        this.markId = markId;
    }

    public Long getMarkId() 
    {
        return markId;
    }
    public void setMarkContent(Long markContent) 
    {
        this.markContent = markContent;
    }

    public Long getMarkContent() 
    {
        return markContent;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("markId", getMarkId())
            .append("markContent", getMarkContent())
            .append("studentId", getStudentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
