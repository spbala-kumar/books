package com.books.bookstores.service;

import com.books.bookstores.model.Basket;
import com.books.bookstores.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookPriceService {

    private final DiscountStrategy discountUtil;

    @Autowired

    public BookPriceService(DiscountStrategy discountUtil) {
        this.discountUtil = discountUtil;
    }
    private static final double[] DISCOUNTS = {0, 0.05, 0.10, 0.20, 0.25};

    private static final int BOOK_PRICE = 50;
    public double calculatePrice(Basket basket) {
        double calculatedPrice = 0;

        if (basket == null || basket.getBookList() == null || basket.getBookList().isEmpty()) {
            return 0;
        }
        Map<String, Integer> bookCounts = new HashMap<>();
        for (Book book : basket.getBookList()) {
            bookCounts.put(book.getBookTitle(), bookCounts.getOrDefault(book.getBookTitle(), 0) + 1);
        }
        // Process groups of unique books
        while (!bookCounts.isEmpty()) {
            int distinctBooksCount = 0;
            for (String bookTitle : bookCounts.keySet().toArray(new String[0])) {
                distinctBooksCount++;
                bookCounts.put(bookTitle, bookCounts.get(bookTitle) - 1);
                if (bookCounts.get(bookTitle) == 0) {
                    bookCounts.remove(bookTitle);
                }
            }
            // Apply discount for the current group
            double discount = discountUtil.calculateDiscount(distinctBooksCount);
            calculatedPrice += distinctBooksCount * BOOK_PRICE * (1 - discount);
        }
         return calculatedPrice;
    }
}
