package ru.tigrbank.finance.infrastructure.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tigrbank.finance.application.interfaces.DataTransferService;
import ru.tigrbank.finance.application.interfaces.ImportResult;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;

import java.io.File;
import java.util.List;

public class JsonDataTransferService implements DataTransferService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void exportData(List<BankAccount> accounts,
                           List<Operation> operations,
                           String path) {
        try {
            mapper.writeValue(new File(path), new ExportWrapper(accounts, operations));
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }

    @Override
    public ImportResult importData(String path) {
        try {
            ExportWrapper wrapper =
                    mapper.readValue(new File(path), ExportWrapper.class);
            return new ImportResult(wrapper.accounts(), wrapper.operations());
        } catch (Exception e) {
            throw new RuntimeException("JSON import failed", e);
        }
    }

    private record ExportWrapper(
            List<BankAccount> accounts,
            List<Operation> operations
    ) {}
}
