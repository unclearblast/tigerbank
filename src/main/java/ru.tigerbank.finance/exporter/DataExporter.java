package ru.tigrbank.finance.exporter;

import ru.tigrbank.finance.dto.DataBundle;

public interface DataExporter {

    void export(DataBundle data, String file);

}
