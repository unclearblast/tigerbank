package ru.tigrbank.finance.facade;

import ru.tigrbank.finance.domain.BankAccount;
import ru.tigrbank.finance.factory.BankAccountFactory;
import ru.tigrbank.finance.repository.BankAccountRepository;

import java.util.List;

public class BankAccountFacade {

    private final BankAccountRepository repository;
    private final BankAccountFactory factory;

    public BankAccountFacade(
            BankAccountRepository repository,
            BankAccountFactory factory) {

        this.repository = repository;
        this.factory = factory;
    }

    public BankAccount createAccount(String name) {

        BankAccount account = factory.create(name);

        repository.save(account);

        return account;
    }

    public List<BankAccount> getAll() {
        return repository.findAll();
    }
}
