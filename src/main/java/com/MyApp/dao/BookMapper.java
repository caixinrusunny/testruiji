package com.MyApp.dao;

import com.MyApp.bean.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> searchAll();

    @Select("select * from book where id = #{id}")
    Book searchById(int id);

    @Delete("delete from book where id = #{id}")
    int deleteById(int id);


    @Update("update book set name = #{name},type = #{type},description = #{description} where id = #{id}")//多参数，记得加注解，有映射
    int updateTypeById(Book book);

    @Insert("insert into book(name,type,description) values(#{name},#{type},#{description})")
    int insertOne(Book book);
}

