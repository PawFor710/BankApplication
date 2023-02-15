package com.pawfor710.BankApplication.mapper;

import com.pawfor710.BankApplication.model.Transfer;
import com.pawfor710.BankApplication.model.dto.IncomeTransferDto;
import com.pawfor710.BankApplication.model.dto.TransferDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferMapper {

    public TransferDto mapToDto(Transfer transfer) {
        return TransferDto.builder()
                .title(transfer.getTitle())
                .value(transfer.getValue())
                .recipientId(transfer.getRecipientId())
                .successful(transfer.isSuccessful())
                .build();
    }

    public List<TransferDto> mapListToDto(List<Transfer> transfers) {
        return transfers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public IncomeTransferDto mapToIncomeDto(Transfer transfer) {
        return IncomeTransferDto.builder()
                .title(transfer.getTitle())
                .value(transfer.getValue())
                .senderId(transfer.getUser().getId())
                .build();
    }

    public List<IncomeTransferDto> mapListToIncomeDto(List<Transfer> transfers) {
        return transfers.stream()
                .map(this::mapToIncomeDto)
                .collect(Collectors.toList());
    }
}
