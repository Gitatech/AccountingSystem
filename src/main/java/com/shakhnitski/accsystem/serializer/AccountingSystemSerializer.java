package com.shakhnitski.accsystem.serializer;

import com.shakhnitski.accsystem.entity.AccountingSystem;

import java.io.*;

public class AccountingSystemSerializer {

    public void write(AccountingSystem accountingSystem, String path) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(accountingSystem);
        }
    }

    public AccountingSystem read(String path) throws IOException, ClassNotFoundException {
        AccountingSystem accountingSystem;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            accountingSystem = (AccountingSystem) in.readObject();
        }
        return accountingSystem;
    }

}
