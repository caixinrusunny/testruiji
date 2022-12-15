package com.MyApp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

//项目启动入口---->用@SpringBootApplication
@SpringBootApplication
//在springBoot 项目中 默认包扫描 扫描的路径是 启动类 当前所在的包已经同一级目录下的包 以及同一级目录下的子包
//如果用了@ComponentScan注意默认的包在不在该需要扫描的包下，若不在，要加上！！！！！！！！！！！
@ComponentScan(value = {"与项目有关的_都要放在com_MyApp下","com.MyApp"})

@MapperScan("com.MyApp.dao")//指定dao所在包，可以忽略每一个dao里面每个接口类上面的@Mapper注解
// 当前这个@MapperScan("com.MyApp.dao")表示把dao包中的所有接口都加载到了spring容器中

//默认加载的配置文件是application开头
public class MyApp {
//    通过主方法启动springboot项目
    public static void main(String[] args) {
        //SpringBoot 框架给我们提供了一个叫SpringApplication工具类
        // 里面又一个run方法就是专门启动SpringBoot项目的
        // 这个run方法有两个参数
        // 第一个参数 传递 启动类的全类名
        // 第二个参数 是主方法的String数组参数名args

        SpringApplication.run(MyApp.class,args);

    }
}
