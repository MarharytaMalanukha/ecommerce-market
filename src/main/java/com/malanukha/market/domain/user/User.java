package com.malanukha.market.domain.user;

import com.malanukha.market.converter.RolesToArrayConverter;
import com.malanukha.market.domain.shopping.ShoppingSession;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String profilePicture;

    @Convert(converter = RolesToArrayConverter.class)
    private Set<Role> roles;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @OneToOne(mappedBy = "user")
    private ShoppingSession shoppingSession;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;
}