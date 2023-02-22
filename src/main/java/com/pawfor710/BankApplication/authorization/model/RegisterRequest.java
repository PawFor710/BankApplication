package com.pawfor710.BankApplication.authorization.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    @Email(message = "username should be email format")
    private String email;
    @Size(min = 8, message = "Password should have at least 8 signs")
    @Size(max = 16, message = "Password shouldn't have more than 16 signs")
    @NotNull
    private String password;
}
