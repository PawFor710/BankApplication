package com.pawfor710.BankApplication.controller;


import com.pawfor710.BankApplication.authorization.service.JwtService;
import com.pawfor710.BankApplication.mapper.TransferMapper;
import com.pawfor710.BankApplication.model.Transfer;
import com.pawfor710.BankApplication.model.User;
import com.pawfor710.BankApplication.model.dto.IncomeTransferDto;
import com.pawfor710.BankApplication.model.dto.TransferDto;
import com.pawfor710.BankApplication.service.TransferService;
import com.pawfor710.BankApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;
    private final JwtService jwtService;
    private final UserService userService;
    private final TransferMapper transferMapper;

    @PostMapping
    public ResponseEntity<Void> makeTransfer(@RequestHeader HttpHeaders headers,
                                             @RequestBody Transfer transfer) throws Exception {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        User currentUser = userService.getCurrentUser(email);
        currentUser.getTransfers().add(transfer);
        transfer.setUser(currentUser);
        transferService.saveTransfer(transfer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sent")
    public ResponseEntity<List<TransferDto>> getCurrentUserTransfers(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        User currentUser = userService.getCurrentUser(email);
        List<Transfer> transfers = transferService.getCurrentUserTransfers(currentUser.getId());
        return ResponseEntity.ok(transferMapper.mapListToDto(transfers));
    }

    @GetMapping("/incomes")
    public ResponseEntity<List<IncomeTransferDto>> getCurrentUserIncomeTransfers(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtService.extractUsername(token);
        User currentUser = userService.getCurrentUser(email);
        List<Transfer> transfers = transferService.getCurrentUserIncomeTransfers(currentUser.getId());
        transfers.removeIf(transfer -> !transfer.isSuccessful());
        return ResponseEntity.ok(transferMapper.mapListToIncomeDto(transfers));
    }
}
