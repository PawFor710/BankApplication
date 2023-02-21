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
public class IncomeTransferDto {

    private String title;
    private String value;
    private Integer senderId;
    private LocalDate date;
    private LocalTime time;
}
