package ru.tigrbank.finance.factory;

import ru.tigrbank.finance.domain.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccountFactory {

    public BankAccount create(String name) {
        return new BankAccount(
                UUID.randomUUID(),
                name,
                BigDecimal.ZERO
        );
    }
}
