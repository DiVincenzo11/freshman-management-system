package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRoleChildMenuMapper;
import com.ruoyi.system.domain.SysRoleChildMenu;
import com.ruoyi.system.service.ISysRoleChildMenuService;

/**
 * 中间表Service业务层处理
 * 
 * @author xzr
 * @date 2024-08-19
 */
@Service
public class SysRoleChildMenuServiceImpl implements ISysRoleChildMenuService 
{
    @Autowired
    private SysRoleChildMenuMapper sysRoleChildMenuMapper;

    /**
     * 查询中间表
     * 
     * @param roleId 中间表主键
     * @return 中间表
     */
    @Override
    public SysRoleChildMenu selectSysRoleChildMenuByRoleId(Long roleId)
    {
        return sysRoleChildMenuMapper.selectSysRoleChildMenuByRoleId(roleId);
    }

    /**
     * 查询中间表列表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 中间表
     */
    @Override
    public List<SysRoleChildMenu> selectSysRoleChildMenuList(SysRoleChildMenu sysRoleChildMenu)
    {
        return sysRoleChildMenuMapper.selectSysRoleChildMenuList(sysRoleChildMenu);
    }

    /**
     * 新增中间表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 结果
     */
    @Override
    public int insertSysRoleChildMenu(SysRoleChildMenu sysRoleChildMenu)
    {
        return sysRoleChildMenuMapper.insertSysRoleChildMenu(sysRoleChildMenu);
    }

    /**
     * 修改中间表
     * 
     * @param sysRoleChildMenu 中间表
     * @return 结果
     */
    @Override
    public int updateSysRoleChildMenu(SysRoleChildMenu sysRoleChildMenu)
    {
        return sysRoleChildMenuMapper.updateSysRoleChildMenu(sysRoleChildMenu);
    }

    /**
     * 批量删除中间表
     * 
     * @param roleIds 需要删除的中间表主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleChildMenuByRoleIds(Long[] roleIds)
    {
        return sysRoleChildMenuMapper.deleteSysRoleChildMenuByRoleIds(roleIds);
    }

    /**
     * 删除中间表信息
     * 
     * @param roleId 中间表主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleChildMenuByRoleId(Long roleId)
    {
        return sysRoleChildMenuMapper.deleteSysRoleChildMenuByRoleId(roleId);
    }
}
