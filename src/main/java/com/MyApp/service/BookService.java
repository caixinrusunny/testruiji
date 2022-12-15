package com.MyApp.service;

import com.MyApp.bean.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAll();

    public Book getBookById(int id);

    public Boolean deleteBookById(int id);

    public Boolean updteBookTypeById(Book book);

    public Boolean insertBook(Book book);
}
