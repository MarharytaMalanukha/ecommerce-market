package com.malanukha.market.repository.user;

import com.malanukha.market.domain.user.User;
import com.malanukha.market.repository.product.BaseAdminRepository;

public interface UserRepository extends BaseAdminRepository<User, Long> {

    User findByUsername(String username);
}