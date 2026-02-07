package ru.tigrbank.finance.application.service;

import ru.tigrbank.finance.application.interfaces.OperationService;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;
import ru.tigrbank.finance.domain.repository.Repository;

public class OperationServiceImpl implements OperationService {

    private final Repository<Operation> operationRepo;
    private final Repository<BankAccount> accountRepo;

    public OperationServiceImpl(Repository<Operation> operationRepo,
                                Repository<BankAccount> accountRepo) {
        this.operationRepo = operationRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public void add(Operation operation) {
        BankAccount account = accountRepo.findById(operation.getBankAccountId())
                .orElseThrow(() -> new IllegalStateException("Account not found"));

        account.apply(operation);
        operationRepo.save(operation);
    }
}
