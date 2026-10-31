package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ChildMenu;

/**
 * 子菜单管理Mapper接口
 * 
 * @author xzr
 * @date 2024-08-19
 */
public interface ChildMenuMapper 
{
    /**
     * 查询子菜单管理
     * 
     * @param childMenuId 子菜单管理主键
     * @return 子菜单管理
     */
    public ChildMenu selectChildMenuByChildMenuId(Long childMenuId);

    /**
     * 查询子菜单管理列表
     * 
     * @param childMenu 子菜单管理
     * @return 子菜单管理集合
     */
    public List<ChildMenu> selectChildMenuList(ChildMenu childMenu);

    /**
     * 新增子菜单管理
     * 
     * @param childMenu 子菜单管理
     * @return 结果
     */
    public int insertChildMenu(ChildMenu childMenu);

    /**
     * 修改子菜单管理
     * 
     * @param childMenu 子菜单管理
     * @return 结果
     */
    public int updateChildMenu(ChildMenu childMenu);

    /**
     * 删除子菜单管理
     * 
     * @param childMenuId 子菜单管理主键
     * @return 结果
     */
    public int deleteChildMenuByChildMenuId(Long childMenuId);

    /**
     * 批量删除子菜单管理
     * 
     * @param childMenuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChildMenuByChildMenuIds(Long[] childMenuIds);
}
