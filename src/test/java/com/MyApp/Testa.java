package com.MyApp;

import com.MyApp.读取yml文件.bean.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyApp.class)
//关联启动类,如果没有此属性设置,则默认将Test类放在与启动类同级目录下,
// 注意: src的main与test被spring认为同一级且一样的目录
// 即启动类在main/java/com/itheima,则Test要在test/java/com/itheima(默认)
public class Testa {

    @Autowired
    private user user;

    @Test
    public void test(){
        System.out.println(user);
    }

}
