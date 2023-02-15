package com.pawfor710.BankApplication.mapper;

import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .accountNumber(user.getAccountNumber())
                .balance(user.getBalance())
                .build();
    }
}
