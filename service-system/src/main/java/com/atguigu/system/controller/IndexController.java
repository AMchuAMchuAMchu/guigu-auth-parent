package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu.system.controller
 * Version ==> 1.0
 * CreateTime ==> 2023-02-20 20:55:35
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
@Api(tags = "用户登录接口")
@RequestMapping("/admin/system/index")
@RestController
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){

        SysUser sysUser =sysUserService.getUserInfoByUsername(loginVo.getUsername());

        if (sysUser == null){
            throw new GuiguException(20001,"用户不存在!!");
        }

        String password = loginVo.getPassword();

        String MD5Password = MD5.encrypt(password);

        if (!sysUser.getPassword().equals(MD5Password)){
            throw new GuiguException(20001,"密码不正确!!");
        }

        if (sysUser.getStatus() == 0){
            throw new GuiguException(20001,"用户已经被禁用!!");
        }


        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        HashMap<String, Object> login = new HashMap<>();
        login.put("token",token);
        return Result.ok(login);
    }

//    code
//:
//20000
//data
//:
//{roles: ["admin"], introduction: "I am a super administrator",…}
//avatar
//:
//"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
//introduction
//:
//"I am a super administrator"
//name
//:
//"Super Admin"

    @ApiOperation("信息")
    @GetMapping("/info")
    public Result info(HttpServletRequest request){

        String token = request.getHeader("token");

        String username = JwtHelper.getUsername(token);

        Map<String,Object> map = sysUserService.getUserInfo(username);

        return Result.ok(map);
    }



}
