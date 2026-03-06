package ru.tigrbank.finance.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tigrbank.finance.dto.DataBundle;

import java.util.List;

public class JsonDataImporter extends AbstractDataImporter {

    @Override
    protected DataBundle parse(List<String> lines) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            return mapper.readValue(
                    String.join("\n", lines),
                    DataBundle.class
            );

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
