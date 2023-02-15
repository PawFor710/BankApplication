package com.pawfor710.BankApplication.controller;

import com.pawfor710.BankApplication.authorization.service.JwtService;
import com.pawfor710.BankApplication.mapper.UserMapper;
import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.model.dto.UserDto;
import com.pawfor710.BankApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        User currentUser = userService.getCurrentUser(email);
        return ResponseEntity.ok(userMapper.mapToDto(currentUser));
    }


}

