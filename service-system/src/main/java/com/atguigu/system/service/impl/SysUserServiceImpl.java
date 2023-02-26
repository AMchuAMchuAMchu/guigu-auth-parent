package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.RouterVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.mapper.SysUserMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> sysUserPage, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(sysUserPage,sysUserQueryVo);
    }

    //更改用户状态
    @Override
    public void updateStatus(String id, Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
    }

    //根据用户名查询用户信息
    @Override
    public SysUser getUserInfoByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username",username);

        return baseMapper.selectOne(queryWrapper);
    }

    //根据用户名获取用户信息
    @Override
    public Map<String, Object> getUserInfo(String username) {
        SysUser sysUser = this.getUserInfoByUsername(username);

        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        List<String> buttonsList = sysMenuService.getUserButtonList(sysUser.getId());

        HashMap<String, Object> map = new HashMap<>();
        map.put("name",username);
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        map.put("roles","[\"admin\"]");
        map.put("routers",routerVoList);
        map.put("buttons",buttonsList);
        return map;
    }
}
