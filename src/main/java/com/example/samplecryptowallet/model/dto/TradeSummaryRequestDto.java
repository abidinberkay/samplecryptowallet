package com.example.samplecryptowallet.model.dto;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Getter
public class TradeSummaryRequestDto {

    private Long userId;
    private Date startDate;
    private Date endDate;

}
