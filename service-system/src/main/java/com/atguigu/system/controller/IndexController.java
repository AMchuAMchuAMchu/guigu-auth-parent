package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @PostMapping("/login")
    public Result login(){
        HashMap<String, Object> login = new HashMap<>();
        login.put("token","admin-token atguigu");
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

    @GetMapping("/info")
    public Result info(){
        HashMap<String, Object> info = new HashMap<>();
        info.put("roles","[admin]");
        info.put("introduction","I am a super administrator");
        info.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name","Super Admin atguigu");
        return Result.ok(info);
    }



}
