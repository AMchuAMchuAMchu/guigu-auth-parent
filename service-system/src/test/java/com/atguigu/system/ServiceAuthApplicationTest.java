package com.atguigu.system;

import com.atguigu.system.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu
 * Version ==> 1.0
 * CreateTime ==> 2023-01-14 09:33:25
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
@SpringBootTest
public class ServiceAuthApplicationTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testAdd(){
//        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
//        System.out.println(sysRoles);


//        SysRole sysRole = new SysRole();
//        sysRole.setRoleCode("1004");
//        sysRole.setDescription("练习时长不够两年");
//        sysRole.setRoleCode("10004");
//        sysRoleMapper.insert(sysRole);


        List<SysRole> list = sysRoleService.list();

        System.out.println(">>>"+list);
    }

}
