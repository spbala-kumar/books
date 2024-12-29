package com.books.bookstores.service;

import com.books.bookstores.controller.BookPriceController;
import com.books.bookstores.model.Basket;
import com.books.bookstores.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookPriceService {
    private static final Logger logger= LoggerFactory.getLogger(BookPriceService.class);


    private final DiscountStrategy discountStrategy;

    @Autowired

    public BookPriceService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    private static final double[] DISCOUNTS = {0, 0.05, 0.10, 0.20, 0.25};

    private static final int BOOK_PRICE = 50;
    public double calculatePrice(Basket basket) {
        double calculatedPrice = 0;

        if (basket == null || basket.getBookList() == null || basket.getBookList().isEmpty()) {
            logger.warn("Recieived an empty or null with Basket for calculate price");
            return 0;
        }
        logger.info("Price calculation for book list : {} " , basket.getBookList().size());

        Map<String, Integer> bookCounts = new HashMap<>();
        for (Book book : basket.getBookList()) {
            bookCounts.put(book.getBookTitle(), bookCounts.getOrDefault(book.getBookTitle(), 0) + 1);
        }
        logger.debug("Initial Book counts: {} ",bookCounts);
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
            double discount = discountStrategy.calculateDiscount(distinctBooksCount);
            double groupCost = distinctBooksCount * BOOK_PRICE * (1 - discount);
            logger.debug("Processed group of {} distinct books with discount {} and group cost : {} ",distinctBooksCount,discount,groupCost);
            calculatedPrice +=groupCost;

        }
        logger.debug("calculatedPrice: {} ",calculatedPrice);
         return calculatedPrice;
    }
}
