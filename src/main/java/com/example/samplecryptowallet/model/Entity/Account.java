package com.example.samplecryptowallet.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_currency_type")
    private String accountCurrencyType;

}
