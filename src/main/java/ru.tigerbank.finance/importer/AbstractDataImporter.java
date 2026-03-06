package ru.tigrbank.finance.importer;

import ru.tigrbank.finance.dto.DataBundle;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractDataImporter {

    public final DataBundle importData(String file) {

        List<String> lines = readFile(file);

        DataBundle data = parse(lines);

        process(data);

        return data;
    }

    protected abstract DataBundle parse(List<String> lines);

    protected void process(DataBundle data) {

        System.out.println("Processing imported data...");
    }

    private List<String> readFile(String file) {

        try {
            return Files.readAllLines(Path.of(file));
        }
        catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
