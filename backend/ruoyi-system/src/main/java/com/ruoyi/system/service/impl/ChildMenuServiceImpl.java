package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.SysRoleChildMenu;
import com.ruoyi.system.mapper.SysRoleChildMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChildMenuMapper;
import com.ruoyi.system.domain.ChildMenu;
import com.ruoyi.system.service.IChildMenuService;

/**
 * 子菜单管理Service业务层处理
 *
 * @author xzr
 * @date 2024-08-19
 */
@Service
public class ChildMenuServiceImpl implements IChildMenuService
{
    @Autowired
    private ChildMenuMapper childMenuMapper;
    @Autowired
    private SysRoleChildMenuMapper sysRoleChildMenuMapper;

    /**
     * 查询子菜单管理
     *
     * @param childMenuId 子菜单管理主键
     * @return 子菜单管理
     */
    @Override
    public ChildMenu selectChildMenuByChildMenuId(Long childMenuId)
    {
        return childMenuMapper.selectChildMenuByChildMenuId(childMenuId);
    }

    /**
     * 查询子菜单管理列表
     *
     * @param childMenu 子菜单管理
     * @return 子菜单管理
     */
    @Override
    public List<ChildMenu> selectChildMenuList(ChildMenu childMenu)
    {
        return childMenuMapper.selectChildMenuList(childMenu);
    }

    /**
     * 新增子菜单管理
     *
     * @param childMenu 子菜单管理
     * @return 结果
     */
    @Override
    public int insertChildMenu(ChildMenu childMenu)
    {
        int i = childMenuMapper.insertChildMenu(childMenu);
        if(i>0){
            SysRoleChildMenu sysRoleChildMenu=new SysRoleChildMenu();
            sysRoleChildMenu.setRoleId(1L);
            sysRoleChildMenu.setChildMenuId(childMenu.getChildMenuId());
            sysRoleChildMenuMapper.insertSysRoleChildMenu(sysRoleChildMenu);
        }
        return i;
    }

    /**
     * 修改子菜单管理
     *
     * @param childMenu 子菜单管理
     * @return 结果
     */
    @Override
    public int updateChildMenu(ChildMenu childMenu)
    {
        return childMenuMapper.updateChildMenu(childMenu);
    }

    /**
     * 批量删除子菜单管理
     *
     * @param childMenuIds 需要删除的子菜单管理主键
     * @return 结果
     */
    @Override
    public int deleteChildMenuByChildMenuIds(Long[] childMenuIds)
    {
        int i = childMenuMapper.deleteChildMenuByChildMenuIds(childMenuIds);
        if(i>0){
            return sysRoleChildMenuMapper.deleteSysRoleChildMenuByRoleIds(childMenuIds);
        }
        return i;
    }

    /**
     * 删除子菜单管理信息
     *
     * @param childMenuId 子菜单管理主键
     * @return 结果
     */
    @Override
    public int deleteChildMenuByChildMenuId(Long childMenuId)
    {

        int i = childMenuMapper.deleteChildMenuByChildMenuId(childMenuId);
        if (i>0){
            return sysRoleChildMenuMapper.deleteSysRoleChildMenuByRoleId(childMenuId);
        }
        return i;
    }
}
