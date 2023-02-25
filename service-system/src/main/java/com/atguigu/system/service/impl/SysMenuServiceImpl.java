package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.system.SysRoleMenu;
import com.atguigu.model.vo.AssginMenuVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.mapper.SysRoleMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.utils.MenuHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.MenuListener;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-22
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    //    菜单列表
    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenus = baseMapper.selectList(null);

        List<SysMenu> resultLists = MenuHelper.buildTree(sysMenus);

        return resultLists;
    }

    @Override
    public void removeMenuById(String id) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("parent_id", id);

        Integer count = baseMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new GuiguException(201,"请先删除子菜单!!");
        }

        baseMapper.deleteById(id);

    }

//    根据角色获取菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {

        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();

        sysMenuQueryWrapper.eq("status",1);

        List<SysMenu> sysMenus = baseMapper.selectList(sysMenuQueryWrapper);

        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();

        sysRoleMenuQueryWrapper.eq("role_id",roleId);

        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(sysRoleMenuQueryWrapper);

        ArrayList<String> roleMenuIds = new ArrayList<>();

        sysRoleMenus.forEach(item->{
            roleMenuIds.add(item.getMenuId());
        });

        sysMenus.forEach(item->{
            item.setSelect(roleMenuIds.contains(item.getId()));
        });

        List<SysMenu> resultSysMenu = MenuHelper.buildTree(sysMenus);
        return resultSysMenu;
    }

    //给用户分配菜单
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();

        sysRoleMenuQueryWrapper.eq("role_id",assginMenuVo.getRoleId());

        sysRoleMenuMapper.delete(sysRoleMenuQueryWrapper);

        List<String> menuIdList = assginMenuVo.getMenuIdList();

        menuIdList.forEach(item->{
            SysRoleMenu sysRoleMenu = new SysRoleMenu();

            sysRoleMenu.setMenuId(item);

            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());

            sysRoleMenuMapper.insert(sysRoleMenu);
        });


    }
}
