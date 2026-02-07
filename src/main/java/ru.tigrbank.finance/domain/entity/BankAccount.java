package ru.tigrbank.finance.domain.entity;

import ru.tigrbank.finance.domain.enums.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {

    private final UUID id;
    private String name;
    private BigDecimal balance;

    public BankAccount(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.balance = BigDecimal.ZERO;
    }

    public void apply(Operation operation) {
        if (operation.getType() == OperationType.INCOME) {
            balance = balance.add(operation.getAmount());
        } else {
            balance = balance.subtract(operation.getAmount());
        }
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }
}
