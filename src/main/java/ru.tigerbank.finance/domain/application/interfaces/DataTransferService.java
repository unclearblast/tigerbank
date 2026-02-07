package ru.tigrbank.finance.application.interfaces;

import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;

import java.util.List;

public interface DataTransferService {

    void exportData(List<BankAccount> accounts, List<Operation> operations, String path);

    ImportResult importData(String path);
}
