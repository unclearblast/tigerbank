package ru.tigrbank.finance;

import org.junit.jupiter.api.Test;
import ru.tigrbank.finance.application.service.AnalyticsServiceImpl;
import ru.tigrbank.finance.domain.entity.Operation;
import ru.tigrbank.finance.domain.enums.OperationType;
import ru.tigrbank.finance.infrastructure.repository.InMemoryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyticsServiceTest {

    @Test
    void profitCalculatedCorrectly() {
        var repo = new InMemoryRepository<Operation>();

        repo.save(new Operation(OperationType.INCOME, null, null,
                BigDecimal.valueOf(1000), LocalDate.now(), null));

        repo.save(new Operation(OperationType.EXPENSE, null, null,
                BigDecimal.valueOf(400), LocalDate.now(), null));

        var service = new AnalyticsServiceImpl(repo);

        assertEquals(BigDecimal.valueOf(600),
                service.calculateProfit(LocalDate.MIN, LocalDate.MAX));
    }
}
