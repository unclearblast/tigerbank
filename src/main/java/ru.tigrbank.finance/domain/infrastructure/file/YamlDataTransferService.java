package ru.tigrbank.finance.infrastructure.file;

import org.yaml.snakeyaml.Yaml;
import ru.tigrbank.finance.application.interfaces.DataTransferService;
import ru.tigrbank.finance.application.interfaces.ImportResult;
import ru.tigrbank.finance.domain.entity.BankAccount;
import ru.tigrbank.finance.domain.entity.Operation;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class YamlDataTransferService implements DataTransferService {

    private final Yaml yaml = new Yaml();

    @Override
    public void exportData(List<BankAccount> accounts,
                           List<Operation> operations,
                           String path) {
        try (FileWriter writer = new FileWriter(path)) {
            yaml.dump(Map.of(
                    "accounts", accounts,
                    "operations", operations
            ), writer);
        } catch (Exception e) {
            throw new RuntimeException("YAML export failed", e);
        }
    }

    @Override
    public ImportResult importData(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            Map<String, Object> data = yaml.load(fis);
            return new ImportResult(
                    (List<BankAccount>) data.get("accounts"),
                    (List<Operation>) data.get("operations")
            );
        } catch (Exception e) {
            throw new RuntimeException("YAML import failed", e);
        }
    }
}
