package com.malanukha.market.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAdminRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
