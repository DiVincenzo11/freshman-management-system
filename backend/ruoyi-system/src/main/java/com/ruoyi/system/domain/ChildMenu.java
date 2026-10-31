package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 子菜单管理对象 child_menu
 *
 * @author xzr
 * @date 2024-08-19
 */
public class ChildMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long childMenuId;

    /** 实体 */
    @Excel(name = "实体")
    private String childMenuClass;

    /** 字段名称 */
    @Excel(name = "字段标识")
    private String childMenuName;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long orderNum;

    /** 类型 */
    @Excel(name = "类型")
    private Long childMenuType;

    /**字段名称*/
    private String childMenuLabel;

    public String getChildMenuLabel() {
        return childMenuLabel;
    }

    public void setChildMenuLabel(String childMenuLabel) {
        this.childMenuLabel = childMenuLabel;
    }

    public void setChildMenuId(Long childMenuId)
    {
        this.childMenuId = childMenuId;
    }

    public Long getChildMenuId()
    {
        return childMenuId;
    }
    public void setChildMenuClass(String childMenuClass)
    {
        this.childMenuClass = childMenuClass;
    }

    public String getChildMenuClass()
    {
        return childMenuClass;
    }
    public void setChildMenuName(String childMenuName)
    {
        this.childMenuName = childMenuName;
    }

    public String getChildMenuName()
    {
        return childMenuName;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum()
    {
        return orderNum;
    }
    public void setChildMenuType(Long childMenuType)
    {
        this.childMenuType = childMenuType;
    }

    public Long getChildMenuType()
    {
        return childMenuType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("childMenuId", getChildMenuId())
                .append("childMenuClass", getChildMenuClass())
                .append("childMenuName", getChildMenuName())
                .append("orderNum", getOrderNum())
                .append("childMenuType", getChildMenuType())
                .toString();
    }
}
