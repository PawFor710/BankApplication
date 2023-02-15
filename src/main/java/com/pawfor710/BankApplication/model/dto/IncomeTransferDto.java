package com.pawfor710.BankApplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTransferDto {

    private String title;
    private BigDecimal value;
    private Integer senderId;
}
