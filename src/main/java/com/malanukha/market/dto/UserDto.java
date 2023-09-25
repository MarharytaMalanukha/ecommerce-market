package com.malanukha.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String profilePicture;
    private String role;
    private String primaryAddress;
    private String primaryPaymentType;
    private String primaryProvider;
}