package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.AccountingSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountingSystemService {
    public static Logger logger = LogManager.getLogger();

    private static AccountingSystemService instance;

    private AccountingSystemService() {}

    public static AccountingSystemService getInstance() {
        if (instance == null) {
            instance = new AccountingSystemService();
        }
        return instance;
    }

    public int calculateNumberOfHouses(AccountingSystem accountingSystem) {
        int result = accountingSystem.size();
        logger.info("Number of houses in accounting system is {}", result);
        return result;
    }

    public int calculateNumberOfApartments(AccountingSystem accountingSystem) {
        int result = accountingSystem.getHouses().parallelStream().mapToInt(e -> e.getApartments().size()).sum();
        logger.info("Number of apartments in accounting system is {}", result);
        return result;
    }
}
