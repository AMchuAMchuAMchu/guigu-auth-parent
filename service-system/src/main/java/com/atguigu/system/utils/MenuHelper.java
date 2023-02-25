package com.atguigu.system.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.List;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu.system.utils
 * Version ==> 1.0
 * CreateTime ==> 2023-02-25 13:32:11
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
public class MenuHelper {


    public static List<SysMenu> buildTree(List<SysMenu> sysMenus) {
        ArrayList<SysMenu> trees = new ArrayList<>();

        sysMenus.forEach(item->{
            if (item.getParentId().longValue() == 0){
                trees.add(findChild(item,sysMenus));
            }
        });

        return trees;
    }

    private static SysMenu findChild(SysMenu sysMenu, List<SysMenu> sysMenus) {

        sysMenu.setChildren(new ArrayList<SysMenu>());

        sysMenus.forEach(item->{
            if (Long.valueOf(sysMenu.getId())==item.getParentId()){
                if (sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<SysMenu>());
                }
                sysMenu.getChildren().add(findChild(item,sysMenus));
            }
        });


        return sysMenu;
    }
}
