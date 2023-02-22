package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.mapper.SysUserRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu.system.service.impl
 * Version ==> 1.0
 * CreateTime ==> 2023-01-14 10:31:31
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo) {
        IPage<SysRole> pageModel = sysRoleMapper.selectPage(pageParam, roleQueryVo);
        return pageModel;
    }

    @Override
    public Map<String, Object> getRolesByUerId(String userId) {

        List<SysRole> sysRoles = baseMapper.selectList(null);

        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id",userId);

        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWrapper);

        ArrayList<String> userRoleIds = new ArrayList<>();

        sysUserRoles.forEach((item)-> userRoleIds.add(item.getRoleId()));

        HashMap<String, Object> userRoleMap = new HashMap<>();

        userRoleMap.put("allRoles",sysRoles);

        userRoleMap.put("userRoleIds",userRoleIds);

        return userRoleMap;
    }

    //给用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id",assginRoleVo.getUserId());

        sysUserRoleMapper.delete(queryWrapper);

        List<String> roleIdList = assginRoleVo.getRoleIdList();

        roleIdList.forEach((item)->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRoleMapper.insert(sysUserRole);
        });




    }
}
