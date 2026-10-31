package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生管理对象 student
 *
 * @author xzr
 * @date 2024-08-18
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String studentName;

    /** 学号 */
    @Excel(name = "学号")
    private String studentNum;

    /** 专业班级 */
    @Excel(name = "专业班级", dictType = "student_grades")
    private Long grades;

    /** 班级职务 */
    @Excel(name = "班级职务", dictType = "student_duties")
    private Long duties;

    /** 性别 */
    @Excel(name = "性别", dictType = "sys_user_sex")
    private Long sex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 宿舍号 */
    @Excel(name = "宿舍号")
    private String dormitory;

    /** 政治面貌 */
    @Excel(name = "政治面貌", dictType = "student_face")
    private Long face;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** QQ */
    @Excel(name = "QQ")
    private String oicq;

    /** 微信号 */
    @Excel(name = "微信号")
    private String weChat;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String address;

    /** 紧急联系人1姓名 */
    @Excel(name = "紧急联系人1姓名")
    private String emergencyName1;

    /** 紧急联系人1与生关系 */
    @Excel(name = "紧急联系人1与生关系")
    private String emergencyRelation1;

    /** 紧急联系人1工作或就读单位 */
    @Excel(name = "紧急联系人1工作或就读单位")
    private String emergencyWork1;

    /** 紧急联系人1手机号 */
    @Excel(name = "紧急联系人1手机号")
    private String emergencyPhone1;

    /** 紧急联系人2姓名 */
    @Excel(name = "紧急联系人2姓名")
    private String emergencyName2;

    /** 紧急联系人2与生关系 */
    @Excel(name = "紧急联系人2与生关系")
    private String emergencyRelation2;

    /** 紧急联系人2工作或就读单位 */
    @Excel(name = "紧急联系人2工作或就读单位")
    private String emergencyWork2;

    /** 紧急联系人2手机号 */
    @Excel(name = "紧急联系人2手机号")
    private String emergencyPhone2;

    /** 1寸照片 */
    @Excel(name = "1寸照片",cellType = Excel.ColumnType.IMAGE)
    private String photo;

    /** 生活照 */
    @Excel(name = "生活照",cellType = Excel.ColumnType.IMAGE)
    private String lifePhoto;

    private String ismark;

    private Long follow;
    @Excel(name = "宗教信仰")
    private String religion;

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getIsmark() {
        return ismark;
    }

    public void setIsmark(String ismark) {
        this.ismark = ismark;
    }

    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    private List<StudentMark> markList;

    public List<StudentMark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<StudentMark> markList) {
        this.markList = markList;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Long getStudentId()
    {
        return studentId;
    }
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentName()
    {
        return studentName;
    }
    public void setStudentNum(String studentNum)
    {
        this.studentNum = studentNum;
    }

    public String getStudentNum()
    {
        return studentNum;
    }
    public void setGrades(Long grades)
    {
        this.grades = grades;
    }

    public Long getGrades()
    {
        return grades;
    }
    public void setDuties(Long duties)
    {
        this.duties = duties;
    }

    public Long getDuties()
    {
        return duties;
    }
    public void setSex(Long sex)
    {
        this.sex = sex;
    }

    public Long getSex()
    {
        return sex;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getIdCard()
    {
        return idCard;
    }
    public void setNativePlace(String nativePlace)
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace()
    {
        return nativePlace;
    }
    public void setNation(String nation)
    {
        this.nation = nation;
    }

    public String getNation()
    {
        return nation;
    }
    public void setDormitory(String dormitory)
    {
        this.dormitory = dormitory;
    }

    public String getDormitory()
    {
        return dormitory;
    }
    public void setFace(Long face)
    {
        this.face = face;
    }

    public Long getFace()
    {
        return face;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setOicq(String oicq)
    {
        this.oicq = oicq;
    }

    public String getOicq()
    {
        return oicq;
    }
    public void setWeChat(String weChat)
    {
        this.weChat = weChat;
    }

    public String getWeChat()
    {
        return weChat;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setEmergencyName1(String emergencyName1)
    {
        this.emergencyName1 = emergencyName1;
    }

    public String getEmergencyName1()
    {
        return emergencyName1;
    }
    public void setEmergencyRelation1(String emergencyRelation1)
    {
        this.emergencyRelation1 = emergencyRelation1;
    }

    public String getEmergencyRelation1()
    {
        return emergencyRelation1;
    }
    public void setEmergencyWork1(String emergencyWork1)
    {
        this.emergencyWork1 = emergencyWork1;
    }

    public String getEmergencyWork1()
    {
        return emergencyWork1;
    }
    public void setEmergencyPhone1(String emergencyPhone1)
    {
        this.emergencyPhone1 = emergencyPhone1;
    }

    public String getEmergencyPhone1()
    {
        return emergencyPhone1;
    }
    public void setEmergencyName2(String emergencyName2)
    {
        this.emergencyName2 = emergencyName2;
    }

    public String getEmergencyName2()
    {
        return emergencyName2;
    }
    public void setEmergencyRelation2(String emergencyRelation2)
    {
        this.emergencyRelation2 = emergencyRelation2;
    }

    public String getEmergencyRelation2()
    {
        return emergencyRelation2;
    }
    public void setEmergencyWork2(String emergencyWork2)
    {
        this.emergencyWork2 = emergencyWork2;
    }

    public String getEmergencyWork2()
    {
        return emergencyWork2;
    }
    public void setEmergencyPhone2(String emergencyPhone2)
    {
        this.emergencyPhone2 = emergencyPhone2;
    }

    public String getEmergencyPhone2()
    {
        return emergencyPhone2;
    }
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getPhoto()
    {
        return photo;
    }
    public void setLifePhoto(String lifePhoto)
    {
        this.lifePhoto = lifePhoto;
    }

    public String getLifePhoto()
    {
        return lifePhoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("studentNum", getStudentNum())
            .append("grades", getGrades())
            .append("duties", getDuties())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("idCard", getIdCard())
            .append("nativePlace", getNativePlace())
            .append("nation", getNation())
            .append("dormitory", getDormitory())
            .append("face", getFace())
            .append("phone", getPhone())
            .append("oicq", getOicq())
            .append("weChat", getWeChat())
            .append("address", getAddress())
            .append("emergencyName1", getEmergencyName1())
            .append("emergencyRelation1", getEmergencyRelation1())
            .append("emergencyWork1", getEmergencyWork1())
            .append("emergencyPhone1", getEmergencyPhone1())
            .append("emergencyName2", getEmergencyName2())
            .append("emergencyRelation2", getEmergencyRelation2())
            .append("emergencyWork2", getEmergencyWork2())
            .append("emergencyPhone2", getEmergencyPhone2())
            .append("photo", getPhoto())
            .append("lifePhoto", getLifePhoto())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
