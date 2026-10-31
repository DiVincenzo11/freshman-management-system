package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleChildMenu;

/**
 * 中间表Mapper接口
 * 
 * @author xzr
 * @date 2024-08-19
 */
public interface SysRoleChildMenuMapper 
{
    /**
     * 查询中间表
     * 
     * @param roleId 中间表主键
     * @return 中间表
     */
    public SysRoleChildMenu selectSysRoleChildMenuByRoleId(Long roleId);

    /**
     * 查询中间表列表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 中间表集合
     */
    public List<SysRoleChildMenu> selectSysRoleChildMenuList(SysRoleChildMenu sysRoleChildMenu);

    /**
     * 新增中间表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 结果
     */
    public int insertSysRoleChildMenu(SysRoleChildMenu sysRoleChildMenu);

    /**
     * 修改中间表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 结果
     */
    public int updateSysRoleChildMenu(SysRoleChildMenu sysRoleChildMenu);

    /**
     * 删除中间表
     * 
     * @param roleId 中间表主键
     * @return 结果
     */
    public int deleteSysRoleChildMenuByRoleId(Long roleId);

    /**
     * 批量删除中间表
     * 
     * @param roleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysRoleChildMenuByRoleIds(Long[] roleIds);
}
