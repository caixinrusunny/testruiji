package com.MyApp.读取yml文件.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//封装yml对象格式的数据，必须先声明该类被spring容器管理
@Component
//使用@ConfigurationProperties注解定义当前实体类读取配置文件的信息
//通过prefix 属性设置读取哪一个数据
@ConfigurationProperties(prefix = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class user {
    private int id;
    private String name;
    private String[] subject;

}
