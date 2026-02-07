package ru.tigrbank.finance.application.service;

import ru.tigrbank.finance.application.interfaces.AnalyticsService;
import ru.tigrbank.finance.domain.entity.Operation;
import ru.tigrbank.finance.domain.enums.OperationType;
import ru.tigrbank.finance.domain.repository.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnalyticsServiceImpl implements AnalyticsService {

    private final Repository<Operation> operationRepo;

    public AnalyticsServiceImpl(Repository<Operation> operationRepo) {
        this.operationRepo = operationRepo;
    }

    @Override
    public BigDecimal calculateProfit(LocalDate from, LocalDate to) {
        return operationRepo.findAll().stream()
                .filter(o -> !o.getDate().isBefore(from) && !o.getDate().isAfter(to))
                .map(o -> o.getType() == OperationType.INCOME
                        ? o.getAmount()
                        : o.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
