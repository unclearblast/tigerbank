package ru.tigrbank.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.tigrbank.finance.factory.*;
import ru.tigrbank.finance.repository.*;
import ru.tigrbank.finance.repository.impl.*;
import ru.tigrbank.finance.facade.*;

@Configuration
public class AppConfig {

    @Bean
    public BankAccountFactory bankAccountFactory() {
        return new BankAccountFactory();
    }

    @Bean
    public OperationFactory operationFactory() {
        return new OperationFactory();
    }

    @Bean
    public BankAccountRepository bankAccountRepository() {
        return new InMemoryBankAccountRepository();
    }

    @Bean
    public OperationRepository operationRepository() {
        return new InMemoryOperationRepository();
    }

    @Bean
    public BankAccountFacade bankAccountFacade(
            BankAccountRepository repo,
            BankAccountFactory factory) {

        return new BankAccountFacade(repo, factory);
    }

    @Bean
    public OperationFacade operationFacade(
            OperationRepository repo,
            OperationFactory factory) {

        return new OperationFacade(repo, factory);
    }
}
