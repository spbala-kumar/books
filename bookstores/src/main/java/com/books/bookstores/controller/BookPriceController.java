package com.books.bookstores.controller;

import com.books.bookstores.model.Basket;
import com.books.bookstores.model.Book;
import com.books.bookstores.service.BookPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookPriceController {
   private static final Logger logger= LoggerFactory.getLogger(BookPriceController.class);
    @Autowired
    private final BookPriceService bookPriceService;

    @Autowired
    public BookPriceController(BookPriceService bookPriceService) {
        this.bookPriceService = bookPriceService;
    }

    @PostMapping("/calculate-price")
    public double calculatePrice(@RequestBody List<String> bookTitles) {
        logger.info("calculatePrice method enter");
        Basket basket = new Basket(bookTitles.stream().map(Book::new).toList());
        return bookPriceService.calculatePrice(basket);
    }
}
