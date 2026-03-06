package ru.tigrbank.finance.command;

import ru.tigrbank.finance.domain.OperationType;
import ru.tigrbank.finance.facade.OperationFacade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateOperationCommand implements Command {

    private final OperationFacade facade;

    private final UUID accountId;
    private final UUID categoryId;
    private final OperationType type;
    private final BigDecimal amount;

    public CreateOperationCommand(
            OperationFacade facade,
            UUID accountId,
            UUID categoryId,
            OperationType type,
            BigDecimal amount) {

        this.facade = facade;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void execute() {

        facade.createOperation(
                accountId,
                categoryId,
                type,
                amount,
                LocalDate.now(),
                "operation"
        );
    }
}
