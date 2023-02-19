package com.atguigu.system;

import java.util.Arrays;

/**
 * Description ==> TODO
 * BelongsProject ==> guigu-auth-parent
 * BelongsPackage ==> com.atguigu.system
 * Version ==> 1.0
 * CreateTime ==> 2023-02-13 06:16:09
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
public class Test1001 {

    public static void main(String[] args) {

        Test1001 test1001 = new Test1001();

        test1001.add("雪乃", "赤瞳", "常守朱", "艾拉");

    }

    public void add(String ...animeName) {
        System.out.println(">>>" + Arrays.toString(animeName));
    }

}
