package com.MyApp.读取yml文件.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Test")
public class Test {

    @Value("${server.port}")
    private int port;
//    @Value("${jdbc.driver}")
//    private String driver;
//    @Value("${arr[2]}")
//    private String arr;


    @RequestMapping("/test12")
    public String test12(){
        System.out.println("port-->"+port);
//        System.out.println("driver-->"+driver);
//        System.out.println("arr-->"+arr);
        return "test12 end";
    }

    @Autowired
    private Environment environment;
    @RequestMapping("/test13")
    public String test13(){
        System.out.println(environment.getProperty("server.port"));
        return "test13 end";
    }

    @Autowired
    user user;
    @RequestMapping("/test14")
    public String test14(){
        System.out.println(user);
        return "test14 end";
    }
}
