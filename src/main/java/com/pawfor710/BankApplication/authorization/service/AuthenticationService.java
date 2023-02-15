package com.pawfor710.BankApplication.authorization.service;

import com.pawfor710.BankApplication.authorization.model.AuthenticationRequest;
import com.pawfor710.BankApplication.authorization.model.AuthenticationResponse;
import com.pawfor710.BankApplication.authorization.model.RegisterRequest;
import com.pawfor710.BankApplication.repository.UserRepository;
import com.pawfor710.BankApplication.authorization.model.Role;
import com.pawfor710.BankApplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountNumber(createAccountNumber())
                .balance(new BigDecimal("10000"))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String createAccountNumber() {
        Random randomizer = new Random();
        StringBuilder accountNumber = new StringBuilder();
        int counter = 0;
        for(int i = 0; i < 26; i++) {
            counter++;
            accountNumber.append(String.valueOf(randomizer.nextInt(0,9)));
            if(counter == 2 || counter == 6 || counter == 10 || counter == 14 || counter == 18 || counter == 22) {
                accountNumber.append(" ");
            }
        }
        return String.valueOf(accountNumber);
    }
}
