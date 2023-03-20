package com.pawfor710.BankApplication.service;


import com.pawfor710.BankApplication.authorization.service.JwtService;
import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;



    public User getCurrentUser(HttpHeaders headers) {
        String token = headers.getFirst(org.springframework.http.HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
