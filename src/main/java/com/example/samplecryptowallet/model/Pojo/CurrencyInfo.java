package com.example.samplecryptowallet.model.Pojo;

import lombok.Data;

@Data
public class CurrencyInfo {

    private String id;
    private String symbol;
    private String name;
    private String image;
    private String current_price;
    private String market_cap;
    private String market_cap_rank;
    private String fully_diluted_valuation;
    private String total_volume;
    private String high_24h;
    private String low_24h;
    private String price_change_24h;
    private String price_change_percentage_24h;
    private String market_cap_change_24h;
    private String market_cap_change_percentage_24h;
    private String circulating_supply;
    private String total_supply;
    private String max_supply;
    private String ath;
    private String ath_change_percentage;
    private String ath_date;
    private String atl;
    private String atl_change_percentage;
    private String atl_date;
    private Roi roi;
    private String last_updated;

    private class Roi {
        private String time;
        private String currency;
        private String percentage;
    }

}
