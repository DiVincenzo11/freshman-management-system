package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 跟进记录对象 student_follow_up
 * 
 * @author xzr
 * @date 2024-08-18
 */
public class StudentFollowUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long followId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 附件 */
    @Excel(name = "附件")
    private String followImage;

    /** 跟进内容 */
    @Excel(name = "跟进内容")
    private String followContent;

    public void setFollowId(Long followId) 
    {
        this.followId = followId;
    }

    public Long getFollowId() 
    {
        return followId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setFollowImage(String followImage) 
    {
        this.followImage = followImage;
    }

    public String getFollowImage() 
    {
        return followImage;
    }
    public void setFollowContent(String followContent) 
    {
        this.followContent = followContent;
    }

    public String getFollowContent() 
    {
        return followContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("followId", getFollowId())
            .append("studentId", getStudentId())
            .append("followImage", getFollowImage())
            .append("followContent", getFollowContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
