package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.AccountingSystem;

public class AccountingSystemService {
    private static AccountingSystemService instance;

    private AccountingSystemService() {}

    public static AccountingSystemService getInstance() {
        if (instance == null) {
            instance = new AccountingSystemService();
        }
        return instance;
    }

    public int calculateNumberOfHouses(AccountingSystem accountingSystem) {
        return accountingSystem.size();
    }

    public int calculateNumberOfApartments(AccountingSystem accountingSystem) {
        return  accountingSystem.getHouses().parallelStream().mapToInt(e -> e.getApartments().size()).sum();
    }
}
