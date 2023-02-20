package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu.system.controller
 * Version ==> 1.0
 * CreateTime ==> 2023-01-14 10:50:23
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean b = sysRoleService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 修改用户信息
     * @param sysRole
     * @return
     */
    @ApiOperation("最终修改")
    @PostMapping("/update")
    public Result updateById(@RequestBody SysRole sysRole){
        boolean b = sysRoleService.updateById(sysRole);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @ApiOperation("根据id查询用户")
    @PostMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable("id") Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    /**
     * 保存用户
     * @param sysRole
     * @return
     */
    @ApiOperation("保存用户")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean save = sysRoleService.save(sysRole);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
                    SysRoleQueryVo roleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, roleQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 查询全部
     * @return
     */
    @ApiOperation("查询所有记录")
    @GetMapping("/findAll")
    public Result findAll(){
        int i = 9/0;
        List<SysRole> sysRoleList = sysRoleService.list();
        return Result.ok(sysRoleList);
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable("id") Long id){
        boolean b = sysRoleService.removeById(id);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

}
