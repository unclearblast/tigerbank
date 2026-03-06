package ru.tigrbank.finance.factory;

import ru.tigrbank.finance.domain.Category;
import ru.tigrbank.finance.domain.OperationType;

import java.util.UUID;

public class CategoryFactory {

    public Category create(OperationType type, String name) {

        return new Category(
                UUID.randomUUID(),
                type,
                name
        );
    }
}
