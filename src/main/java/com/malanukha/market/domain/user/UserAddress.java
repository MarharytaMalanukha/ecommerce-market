package com.malanukha.market.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_primary")
    private boolean primary;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;

    @Override
    public String toString() {
        return String.join(";", country, city, addressLine, postalCode, id + "");
    }
}