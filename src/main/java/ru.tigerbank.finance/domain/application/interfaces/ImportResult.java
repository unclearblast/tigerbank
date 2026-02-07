package ru.tigrbank.finance.application.interfaces;

import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;

import java.util.List;

public record ImportResult(
        List<BankAccount> accounts,
        List<Operation> operations
) {}
