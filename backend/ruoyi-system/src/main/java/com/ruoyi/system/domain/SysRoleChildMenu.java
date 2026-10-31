package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 中间表对象 sys_role_child_menu
 *
 * @author xzr
 * @date 2024-08-19
 */
public class SysRoleChildMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 子菜单ID */
    private Long childMenuId;

    private Long childMenuType;

    private ChildMenu childMenu;

    public ChildMenu getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(ChildMenu childMenu) {
        this.childMenu = childMenu;
    }

    public Long getChildMenuType() {
        return childMenuType;
    }

    public void setChildMenuType(Long childMenuType) {
        this.childMenuType = childMenuType;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }
    public void setChildMenuId(Long childMenuId)
    {
        this.childMenuId = childMenuId;
    }

    public Long getChildMenuId()
    {
        return childMenuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("childMenuId", getChildMenuId())
            .toString();
    }
}
