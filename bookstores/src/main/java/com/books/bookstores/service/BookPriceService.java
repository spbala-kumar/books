package com.books.bookstores.service;

import com.books.bookstores.model.Basket;
import org.springframework.stereotype.Service;

@Service
public class BookPriceService {

    public double calculatePrice(Basket basket) {

        double calculatedPrice = 0;
        if (basket == null || basket.getBookList() == null || basket.getBookList().isEmpty()) {
            return 0;
        }
        return calculatedPrice;
    }
}
