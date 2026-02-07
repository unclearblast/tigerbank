package ru.tigrbank.finance.application.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AnalyticsService {
    BigDecimal calculateProfit(LocalDate from, LocalDate to);
}
