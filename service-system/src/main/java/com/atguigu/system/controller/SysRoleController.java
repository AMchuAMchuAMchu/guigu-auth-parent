package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.system.model.system.SysRole;
import com.atguigu.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * 查询全部
     * @return
     */
    @ApiOperation("查询所有记录")
    @GetMapping("/findAll")
    public Result findAll(){
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
