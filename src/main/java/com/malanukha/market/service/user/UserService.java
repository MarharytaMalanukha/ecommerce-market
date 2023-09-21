package com.malanukha.market.service.user;

import com.malanukha.market.domain.user.Role;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public boolean saveUser(String username,  String rawPassword, String firstName, String lastName,
                            String email, String telephone, String profilePicture) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .telephone(telephone)
                .profilePicture(profilePicture.isBlank() ? null : profilePicture)
                .roles(Set.of(Role.USER))
                .build();
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}