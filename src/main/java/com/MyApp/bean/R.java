package com.MyApp.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class R <T>{
    private int code;
    private String message;
    private T data;

//    成功
    public static <T>R<T> success(T data){
        R<T> r = new R<>();
        r.code=20011;
        r.data=data;
        return r;
    }
//    失败
    public static <T>R<T> error(String message){
        R<T> r = new R<>();
        r.code = 20010;
        r.message=message;
        return r;
    }
}
