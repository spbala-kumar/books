package com.books.bookstores.service;

public interface DiscountStrategy {

    double calculateDiscount(int uniqueBooksCount);
}
