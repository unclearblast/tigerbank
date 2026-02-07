package ru.tigrbank.finance.console;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tigrbank.finance.application.interfaces.AnalyticsService;
import ru.tigrbank.finance.application.interfaces.DataTransferService;
import ru.tigrbank.finance.application.interfaces.ImportResult;
import ru.tigrbank.finance.application.interfaces.OperationService;
import ru.tigrbank.finance.config.AppConfig;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Category;
import ru.tigrbank.finance.domain.entity.Operation;
import ru.tigrbank.finance.domain.enums.OperationType;
import ru.tigrbank.finance.domain.repository.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        Repository<BankAccount> accountRepo =
                context.getBean("bankAccountRepository", Repository.class);

        OperationService operationService =
                context.getBean(OperationService.class);

        AnalyticsService analyticsService =
                context.getBean(AnalyticsService.class);

        // Создание базового счёта и категорий
        BankAccount account = new BankAccount("Основной счет");
        accountRepo.save(account);

        Category salary = new Category("Зарплата", OperationType.INCOME);
        Category cafe = new Category("Кафе", OperationType.EXPENSE);

        // Добавление операций
        operationService.add(new Operation(
                OperationType.INCOME,
                account.getId(),
                salary.getId(),
                BigDecimal.valueOf(100_000),
                LocalDate.now(),
                "Зарплата"
        ));

        operationService.add(new Operation(
                OperationType.EXPENSE,
                account.getId(),
                cafe.getId(),
                BigDecimal.valueOf(2_500),
                LocalDate.now(),
                "Кофе"
        ));

        System.out.println("Текущий баланс: " + account.getBalance());

        System.out.println("Прибыль за месяц: " +
                analyticsService.calculateProfit(
                        LocalDate.now().minusMonths(1),
                        LocalDate.now()));

        // --- Выбор формата импорта/экспорта ---
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите формат для экспорта/импорта:");
        System.out.println("1 - JSON");
        System.out.println("2 - CSV");
        System.out.println("3 - YAML");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        DataTransferService transferService;

        switch (choice) {
            case 1:
                transferService = context.getBean("jsonDataTransferService", DataTransferService.class);
                break;
            case 2:
                transferService = context.getBean("csvDataTransferService", DataTransferService.class);
                break;
            case 3:
                transferService = context.getBean("yamlDataTransferService", DataTransferService.class);
                break;
            default:
                System.out.println("Неверный выбор, используем JSON по умолчанию.");
                transferService = context.getBean("jsonDataTransferService", DataTransferService.class);
        }

        String filePath = switch (choice) {
            case 1 -> "finance-data.json";
            case 2 -> "finance-data.csv";
            case 3 -> "finance-data.yaml";
            default -> "finance-data.json";
        };

        // Экспорт данных
        transferService.exportData(
                List.of(account),
                List.of(
                        new Operation(OperationType.INCOME, account.getId(), salary.getId(), BigDecimal.valueOf(100_000), LocalDate.now(), "Зарплата"),
                        new Operation(OperationType.EXPENSE, account.getId(), cafe.getId(), BigDecimal.valueOf(2_500), LocalDate.now(), "Кофе")
                ),
                filePath
        );

        System.out.println("Данные экспортированы в " + filePath);

        // Импорт данных (демонстрация)
        ImportResult imported = transferService.importData(filePath);
        System.out.println("Импортированные счета: " + imported.accounts().size());
        System.out.println("Импортированные операции: " + imported.operations().size());
    }
}
