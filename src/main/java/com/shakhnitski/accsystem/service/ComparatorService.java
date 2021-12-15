package com.shakhnitski.accsystem.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class ComparatorService {
    public static Logger logger = LogManager.getLogger();

    private static ComparatorService instance;

    private ComparatorService() {}

    public static ComparatorService getInstance() {
        if (instance == null) {
            instance = new ComparatorService();
        }
        return instance;
    }

    public <T> char getComparisonSign(T a, T b, Comparator<T> comparator) {
        char result = switch (comparator.compare(a, b)) {
            case 1 -> '>';
            case 0 -> '=';
            case -1 -> '<';
            default -> throw new IllegalStateException("Unexpected value: " + comparator.compare(a, b));
        };
        logger.info("Result of getting comparison sign is {}", result);
        return result;
    }

}
