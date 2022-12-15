package com.MyApp;

import com.MyApp.bean.Book;
import com.MyApp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyApp.class)
public class TestBook {

    @Autowired
    private BookService bookService;

    //查所有书
    @Test
    public void test_getAll(){
        bookService.getAll();
    }

    //通过Id查书
    @Test
    public void test_searchBookById(){
        bookService.getBookById(2);
    }

    //根据id删除书本
    @Test
    public void test_deletedBookById(){
        bookService.deleteBookById(2);
    }

    //根据id修改书本
    @Test
    public void test_updateBookTypeById(){
        Book bookById = bookService.getBookById(4);
        bookById.setType("冒险");
        bookService.updteBookTypeById(bookById);
        bookService.getBookById(4);

    }

    //添加书本
    @Test
    public void test_insertBook(){
        Book book = new Book(1, "西游记", "经典", "四大名著");
        bookService.insertBook(book);
    }
}
