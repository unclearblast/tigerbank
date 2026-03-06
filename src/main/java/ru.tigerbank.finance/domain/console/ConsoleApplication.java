package ru.tigrbank.finance.console;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tigrbank.finance.application.facade.BankAccountFacade;
import ru.tigrbank.finance.application.facade.CategoryFacade;
import ru.tigrbank.finance.application.facade.OperationFacade;
import ru.tigrbank.finance.application.interfaces.AnalyticsService;
import ru.tigrbank.finance.application.interfaces.DataTransferService;
import ru.tigrbank.finance.application.interfaces.ImportResult;
import ru.tigrbank.finance.config.AppConfig;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Category;
import ru.tigrbank.finance.domain.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        BankAccountFacade accountFacade =
                context.getBean(BankAccountFacade.class);

        CategoryFacade categoryFacade =
                context.getBean(CategoryFacade.class);

        OperationFacade operationFacade =
                context.getBean(OperationFacade.class);

        AnalyticsService analyticsService =
                context.getBean(AnalyticsService.class);

        // --- создание счета через фабрику + фасад

        BankAccount account =
                accountFacade.createAccount("Основной счет");

        // --- категории

        Category salary =
                categoryFacade.create("Зарплата", OperationType.INCOME);

        Category cafe =
                categoryFacade.create("Кафе", OperationType.EXPENSE);

        // --- операции через фасад

        operationFacade.createOperation(
                OperationType.INCOME,
                account.getId(),
                salary.getId(),
                BigDecimal.valueOf(100000),
                LocalDate.now(),
                "Зарплата"
        );

        operationFacade.createOperation(
                OperationType.EXPENSE,
                account.getId(),
                cafe.getId(),
                BigDecimal.valueOf(2500),
                LocalDate.now(),
                "Кофе"
        );

        System.out.println("Баланс счета: " + account.getBalance());

        System.out.println("Прибыль за месяц: " +
                analyticsService.calculateProfit(
                        LocalDate.now().minusMonths(1),
                        LocalDate.now()));

        // -------------------------
        // выбор формата
        // -------------------------

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nВыберите формат:");

        System.out.println("1 - JSON");
        System.out.println("2 - CSV");
        System.out.println("3 - YAML");

        int choice = scanner.nextInt();

        DataTransferService transferService;

        switch (choice) {

            case 1 -> transferService =
                    context.getBean("jsonDataTransferService", DataTransferService.class);

            case 2 -> transferService =
                    context.getBean("csvDataTransferService", DataTransferService.class);

            case 3 -> transferService =
                    context.getBean("yamlDataTransferService", DataTransferService.class);

            default -> transferService =
                    context.getBean("jsonDataTransferService", DataTransferService.class);
        }

        String file = switch (choice) {

            case 1 -> "finance-data.json";
            case 2 -> "finance-data.csv";
            case 3 -> "finance-data.yaml";
            default -> "finance-data.json";
        };

        transferService.exportData(
                List.of(account),
                List.of(),
                file
        );

        System.out.println("Экспортировано в " + file);

        ImportResult result = transferService.importData(file);

        System.out.println("Импортировано счетов: " + result.accounts().size());
        System.out.println("Импортировано операций: " + result.operations().size());
    }
}
