package com.books.bookstores.model;

import java.util.List;

public class Basket {

    private List<Book> bookList;

    public Basket(List<Book> list) {
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }




}
