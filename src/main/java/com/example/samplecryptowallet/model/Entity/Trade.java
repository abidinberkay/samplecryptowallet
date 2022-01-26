package com.example.samplecryptowallet.model.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trade")
@Entity
public class Trade {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of user object")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "crypto_name")
    private String cryptoName;

    @Column(name = "currency_dollar_value")
    private BigDecimal currencyDollarValue;

    @Column(name = "usdt_balanc_before_trade")
    private BigDecimal usdtBalanceBeforeTrade;

    @Column(name = "usdt_balance_after_trade")
    private BigDecimal usdtBalanceAfterTrade;

    @Column(name = "crypto_balance_before_trade")
    private BigDecimal cryptoBalanceBeforeTrade;

    @Column(name = "crypto_balance_after_trade")
    private BigDecimal cryptoBalanceAfterTrade;


    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "trade_type")
    private String tradeType;

    @Column(name = "date")
    private Date date;

}
