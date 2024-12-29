package com.books.bookstores.service;

import org.springframework.stereotype.Service;

@Service
public class RegularDiscountStrategyImpl implements DiscountStrategy {
    private static final double[] DISCOUNTS = {0, 0.05, 0.10, 0.20, 0.25};

    @Override
    public double calculateDiscount(int uniqueBooksCount) {
        if (uniqueBooksCount <= 0 || uniqueBooksCount > DISCOUNTS.length) {
            return 0;
        }
        return DISCOUNTS[uniqueBooksCount - 1];
    }
}
