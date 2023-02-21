package com.pawfor710.BankApplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    private String title;
    private String value;
    private Integer recipientId;
    private LocalDate date;
    private LocalTime time;
    private boolean successful;
}
