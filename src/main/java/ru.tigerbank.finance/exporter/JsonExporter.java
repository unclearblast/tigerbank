package ru.tigrbank.finance.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tigrbank.finance.dto.DataBundle;

import java.io.File;

public class JsonExporter implements DataExporter {

    @Override
    public void export(DataBundle data, String file) {

        try {

            new ObjectMapper()
                    .writeValue(new File(file), data);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
