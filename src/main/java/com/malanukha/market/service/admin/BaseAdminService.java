package com.malanukha.market.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class BaseAdminService<T1, T2> {

    protected final JpaRepository<T2, Long> repository;

    public BaseAdminService(JpaRepository<T2, Long> repository) {
        this.repository = repository;
    }

    public Optional<T1> get(Long id) {
        return repository.findById(id).map(this::convertToDto);
    }

    public T1 update(T1 entity) {
        return convertToDto(repository.save(convertFromDto(entity)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<T1> list(Pageable pageable) {
        return repository.findAll(pageable).map(this::convertToDto);
    }

    public int count() {
        return (int) repository.count();
    }

    protected abstract T2 convertFromDto(T1 dto);
    protected abstract T1 convertToDto(T2 entity);
}