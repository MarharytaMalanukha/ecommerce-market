package com.malanukha.market.security;

import com.malanukha.market.domain.user.Role;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.repository.user.UserRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<User> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }

    public boolean hasRole(Role role) {
        return get()
                .map(value -> value.getRoles().contains(role))
                .orElse(false);
    }
}