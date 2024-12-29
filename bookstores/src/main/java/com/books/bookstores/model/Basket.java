package com.books.bookstores.model;

import java.util.List;

public class Basket {

    private List<Book> bookList;


    public Basket(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}





