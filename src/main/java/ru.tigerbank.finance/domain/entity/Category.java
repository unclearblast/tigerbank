package ru.tigrbank.finance.domain.entity;

import ru.tigrbank.finance.domain.enums.OperationType;

import java.util.UUID;

public class Category {

    private final UUID id;
    private final String name;
    private final OperationType type;

    public Category(String name, OperationType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public OperationType getType() { return type; }
}
