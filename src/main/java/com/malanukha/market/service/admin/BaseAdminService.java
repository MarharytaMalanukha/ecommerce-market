package com.malanukha.market.service.admin;

import com.malanukha.market.exception.DtoDoesNotExistException;
import com.malanukha.market.exception.EntityDoesNotExistException;
import com.malanukha.market.repository.product.BaseAdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public abstract class BaseAdminService<T1, T2> {

    protected final BaseAdminRepository<T2, Long> repository;

    public BaseAdminService(BaseAdminRepository<T2, Long> repository) {
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

    public Page<T1> list(Pageable pageable, Specification<T2> filter) {
        return repository.findAll(filter, pageable).map(this::convertToDto);
    }

    public int count() {
        return (int) repository.count();
    }

    protected void validateDto(T1 dto) {
        if (dto == null) {
            throw new DtoDoesNotExistException("The dto object should not be null.");
        }
    }

    protected void validateEntity(T2 entity) {
        if (entity == null) {
            throw new EntityDoesNotExistException("The entity object should not be null.");
        }
    }

    protected abstract T2 convertFromDto(T1 dto);
    protected abstract T1 convertToDto(T2 entity);
}