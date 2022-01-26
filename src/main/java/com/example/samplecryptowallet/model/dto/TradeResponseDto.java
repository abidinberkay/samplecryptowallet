package com.example.samplecryptowallet.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeResponseDto {

    private Long userId;
    private BigDecimal newUsdtBalance;
    private BigDecimal newCryptoBalance;
    private String cryptoCurrencyType;
    private Date tradeDate;
    private String tradeResultMessage;

}
