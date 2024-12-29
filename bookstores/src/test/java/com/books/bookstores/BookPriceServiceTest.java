package com.books.bookstores;

import com.books.bookstores.model.Basket;
import com.books.bookstores.model.Book;
import com.books.bookstores.service.BookPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class BookPriceServiceTest {

  @Autowired
    private BookPriceService bookPriceService;

    private List<String> basket=null;

  @BeforeEach
    public void setUp() {
        basket = new ArrayList<>();
    }


    // Scenario 1 : If Basket with no books  then Calculated price should be 0 EUR
    @Test
    public void testEmptyBasket() {
         Basket basket = new Basket(Collections.emptyList());
        assertEquals(0, bookPriceService.calculatePrice(basket));
    }

    /**
     * Scenario:2 : If Basket with only one book  then Calculated price should be 50 EUR(1 * 50)
     */

    @Test
    public void testNoDiscount() {
        List<String> bookList1 = new ArrayList<>();

        bookList1.add("Clean Code");

        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());
        double expectedPrice = 50.0;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }



    /**
     * Scenario:3 : If Basket with two same books   then Calculated price should be 95.0 EUR(2 * 50 with 5% discount)
     */
    @Test
    public void testTwoDistinctBooksDiscount() {
        List<String> bookList1 = new ArrayList<>();

        bookList1.add("Clean Code");
        bookList1.add("Clean Coder");

        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());
        double expectedPrice = 95.0;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }

    /**
     * Scenario:4 : If Basket with three distinct books
     * then Calculated price should be 135.0 EUR(3 * 50 with 10% discount)
     *
     */

   @Test
    public void testThreeDistinctBooksDiscount() {
        List<String> bookList1 = new ArrayList<>();
        bookList1.add("Clean Code");
        bookList1.add("Clean Coder");
        bookList1.add("Clean Architecture");
        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());
        double expectedPrice = 135.0;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }

    /**
     * Scenario:5 : If Basket with four distinct books
     *      then Calculated price should be 160.0 EUR(4 * 50 with 20%)
     *
     */

    @Test
    public void testFourDistinctBooksDiscount() {
        List<String> bookList1 = new ArrayList<>();
        bookList1.add("Clean Code");
        bookList1.add("Clean Coder");
        bookList1.add("Clean Architecture");
        bookList1.add("Test Driven Development by Example");
        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());
        double expectedPrice = 160.0;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }
    /**
     * Scenario:6 : If Basket with five distinct books
     *         then Calculated price should be 187.5 EUR(5 * 50 with 25%)
     *
     */
    @Test
    public void testFiveDistinctBooksDiscount() {
        List<String> bookList1 = new ArrayList<>();
        bookList1.add("Clean Code");
        bookList1.add("Clean Coder");
        bookList1.add("Clean Architecture");
        bookList1.add("Test Driven Development by Example");
        bookList1.add("Working Effectively With Legacy Code");
        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());
        double expectedPrice = 187.5;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }

    /**
     * Scenario:7 : If Basket with mixed  books
     *         then Calculated price should be based on distinct set
     *
     */
    @Test
    public void testWithMixedBooksBasket() {
        List<String> bookList1 = new ArrayList<>();
        bookList1.add("Clean Code");
        bookList1.add("Clean Code");
        bookList1.add("Clean Coder");
        bookList1.add("Clean Coder");
        bookList1.add("Clean Architecture");
        bookList1.add("Clean Architecture");
        bookList1.add("Test Driven Development by Example");
        bookList1.add("Working Effectively With Legacy Code");
        Basket basket = new Basket(bookList1.stream().map(Book::new).toList());

        double expectedPrice = 322.5;
        assertEquals(expectedPrice, bookPriceService.calculatePrice(basket));
    }










}
