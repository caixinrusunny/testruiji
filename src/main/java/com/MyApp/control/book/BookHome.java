package com.MyApp.control.book;

import com.MyApp.bean.Book;
import com.MyApp.bean.R;
import com.MyApp.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookHome {

    @Autowired
    private BookService bookService;

//    查所有
    @GetMapping()
    public R<List<Book>> searchall(){
        List<Book> all = bookService.getAll();
        R<List<Book>> success = R.success(all);
        return success;

    }

//    添加
    @PostMapping()
    public R<String> insert(@RequestBody Book book){//@RequestBody拿请求体的数据
        Boolean aBoolean = bookService.insertBook(book);
        System.out.println("aBoolean:"+aBoolean);

        if (!aBoolean)
            return R.error("添加失败！");
        else
            return R.success("添加成功！");
    }

//    删除
    @DeleteMapping("/{id}")
    public R<String> deleteBook(@PathVariable int id){//@PathVariable拿URL里面的数据
        Boolean aBoolean = bookService.deleteBookById(id);
        if (!aBoolean)
            return R.error("删除失败！");
        else
            return R.success("删除成功！");
    }

//    查一本
    @GetMapping("/{id}")
    public R<Object> searchBookById(@PathVariable int id){
        Book bookById = bookService.getBookById(id);
        if (bookById == null)
            return R.error("没有此书。");
        else
            return R.success(bookById);
    }

//    修改一本
    @PutMapping()
    public R<String> updateBook(@RequestBody Book book){
        Boolean aBoolean = bookService.updteBookTypeById(book);
        if (!aBoolean)
            return R.error("更新失败！");
        else
            return R.success("更新成功！");
    }
}
