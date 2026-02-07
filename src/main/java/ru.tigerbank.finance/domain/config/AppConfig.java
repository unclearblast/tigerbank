package ru.tigrbank.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tigrbank.finance.application.interfaces.AnalyticsService;
import ru.tigrbank.finance.application.interfaces.OperationService;
import ru.tigrbank.finance.application.service.AnalyticsServiceImpl;
import ru.tigrbank.finance.application.service.OperationServiceImpl;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;
import ru.tigrbank.finance.domain.repository.Repository;
import ru.tigrbank.finance.infrastructure.repository.InMemoryRepository;

@Configuration
public class AppConfig {

    @Bean
    public Repository<BankAccount> bankAccountRepository() {
        return new InMemoryRepository<>();
    }

    @Bean
    public Repository<Operation> operationRepository() {
        return new InMemoryRepository<>();
    }

    @Bean
    public OperationService operationService() {
        return new OperationServiceImpl(operationRepository(), bankAccountRepository());
    }

    @Bean
    public AnalyticsService analyticsService() {
        return new AnalyticsServiceImpl(operationRepository());
    }

    @Bean
    public DataTransferService jsonDataTransferService() {
        return new JsonDataTransferService();
    }

    @Bean
    public DataTransferService csvDataTransferService() {
        return new CsvDataTransferService();
    }

    @Bean
    public DataTransferService yamlDataTransferService() {
        return new YamlDataTransferService();
    }
}
