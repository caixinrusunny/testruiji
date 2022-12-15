package com.MyApp.control;

import com.MyApp.读取yml文件.bean.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class test1 {

    @RequestMapping("/books/{id}")
    public String test11(@PathVariable int id){
        System.out.println("id--->"+id);
        return "Hello";
    }

//    @Value("${server.port}")
    private int port=8080;
//    @Value("${jdbc.driver}")
    private String driver="driver";
//    @Value("${arr[2]}")
    private String arr="12";


    @RequestMapping("/test12")
    public String test12(){
        System.out.println("port-->"+port);
        System.out.println("driver-->"+driver);
        System.out.println("arr-->"+arr);
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
