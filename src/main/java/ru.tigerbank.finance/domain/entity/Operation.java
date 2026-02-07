package ru.tigrbank.finance.domain.entity;

import ru.tigrbank.finance.domain.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Operation {

    private final UUID id;
    private final OperationType type;
    private final UUID bankAccountId;
    private final UUID categoryId;
    private final BigDecimal amount;
    private final LocalDate date;
    private final String description;

    public Operation(OperationType type,
                     UUID bankAccountId,
                     UUID categoryId,
                     BigDecimal amount,
                     LocalDate date,
                     String description) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public UUID getId() { return id; }
    public OperationType getType() { return type; }
    public UUID getBankAccountId() { return bankAccountId; }
    public UUID getCategoryId() { return categoryId; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}
