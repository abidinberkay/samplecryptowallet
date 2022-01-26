package com.example.samplecryptowallet.model.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TradeRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String currency;

    @NotNull
    private String operationType;

}
