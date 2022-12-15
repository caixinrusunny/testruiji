package com.MyApp.service.Impl;

import com.MyApp.bean.Book;
import com.MyApp.dao.BookMapper;
import com.MyApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getAll() {
        List<Book> books = bookMapper.searchAll();
//        for (Book b :books) {
//            System.out.println(b);
//        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = bookMapper.searchById(id);
//        System.out.println(book);
        return book;
    }

    @Override
    public Boolean deleteBookById(int id) {
        int i = bookMapper.deleteById(id);
        return i > 0;
    }

    @Override
    public Boolean updteBookTypeById(Book book) {
        int i = bookMapper.updateTypeById(book);
        return i > 0;
    }

    @Override
    public Boolean insertBook(Book book) {
        int i = bookMapper.insertOne(book);
        return i > 0;

    }
}
