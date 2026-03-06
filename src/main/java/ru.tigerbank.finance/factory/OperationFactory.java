package ru.tigrbank.finance.factory;

import ru.tigrbank.finance.domain.Operation;
import ru.tigrbank.finance.domain.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OperationFactory {

    public Operation create(
            UUID accountId,
            UUID categoryId,
            OperationType type,
            BigDecimal amount,
            LocalDate date,
            String description) {

        return new Operation(
                UUID.randomUUID(),
                type,
                accountId,
                amount,
                date,
                description,
                categoryId
        );
    }
}
