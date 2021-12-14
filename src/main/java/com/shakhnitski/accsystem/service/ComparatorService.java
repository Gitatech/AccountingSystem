package com.shakhnitski.accsystem.service;

import java.util.Comparator;

public class ComparatorService {
    private static ComparatorService instance;

    private ComparatorService() {}

    public static ComparatorService getInstance() {
        if (instance == null) {
            instance = new ComparatorService();
        }
        return instance;
    }

    public <T> char getComparisonSign(T a, T b, Comparator<T> comparator) {
        return switch (comparator.compare(a, b)) {
            case 1 -> '>';
            case 0 -> '=';
            case -1 -> '<';
            default -> throw new IllegalStateException("Unexpected value: " + comparator.compare(a, b));
        };
    }

}
