package ru.tigrbank.finance.infrastructure.repository;

import ru.tigrbank.finance.domain.repository.Repository;

import java.lang.reflect.Method;
import java.util.*;

public class InMemoryRepository<T> implements Repository<T> {

    private final Map<UUID, T> storage = new HashMap<>();

    @Override
    public void save(T entity) {
        try {
            Method getId = entity.getClass().getMethod("getId");
            UUID id = (UUID) getId.invoke(entity);
            storage.put(id, entity);
        } catch (Exception e) {
            throw new RuntimeException("Entity must have getId()");
        }
    }

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
