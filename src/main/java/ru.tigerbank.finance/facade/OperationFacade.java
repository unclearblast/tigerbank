package ru.tigrbank.finance.facade;

import ru.tigrbank.finance.domain.Operation;
import ru.tigrbank.finance.domain.OperationType;
import ru.tigrbank.finance.factory.OperationFactory;
import ru.tigrbank.finance.repository.OperationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OperationFacade {

    private final OperationRepository repository;
    private final OperationFactory factory;

    public OperationFacade(
            OperationRepository repository,
            OperationFactory factory) {

        this.repository = repository;
        this.factory = factory;
    }

    public Operation createOperation(
            UUID accountId,
            UUID categoryId,
            OperationType type,
            BigDecimal amount,
            LocalDate date,
            String description) {

        Operation op = factory.create(
                accountId,
                categoryId,
                type,
                amount,
                date,
                description
        );

        repository.save(op);

        return op;
    }
}
