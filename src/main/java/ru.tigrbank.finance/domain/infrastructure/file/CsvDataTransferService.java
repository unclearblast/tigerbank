package ru.tigrbank.finance.infrastructure.file;

import ru.tigrbank.finance.application.interfaces.DataTransferService;
import ru.tigrbank.finance.application.interfaces.ImportResult;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CsvDataTransferService implements DataTransferService {

    @Override
    public void exportData(List<BankAccount> accounts,
                           List<Operation> operations,
                           String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println("id,type,amount,date,accountId,categoryId");
            for (Operation op : operations) {
                writer.printf("%s,%s,%s,%s,%s,%s%n",
                        op.getId(),
                        op.getType(),
                        op.getAmount(),
                        op.getDate(),
                        op.getBankAccountId(),
                        op.getCategoryId());
            }
        } catch (Exception e) {
            throw new RuntimeException("CSV export failed", e);
        }
    }

    @Override
    public ImportResult importData(String path) {
        List<Operation> operations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); // header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                operations.add(new Operation(
                        Enum.valueOf(ru.tigrbank.finance.domain.enums.OperationType.class, p[1]),
                        UUID.fromString(p[4]),
                        UUID.fromString(p[5]),
                        new BigDecimal(p[2]),
                        LocalDate.parse(p[3]),
                        "Imported CSV"
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("CSV import failed", e);
        }

        return new ImportResult(List.of(), operations);
    }
}
