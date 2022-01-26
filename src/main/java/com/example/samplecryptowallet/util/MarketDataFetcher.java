package com.example.samplecryptowallet.util;

import com.example.samplecryptowallet.exception.InvalidCurrencyException;
import com.example.samplecryptowallet.exception.NoAvailableAccountException;
import com.example.samplecryptowallet.model.Pojo.CurrencyInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class MarketDataFetcher {

    private final String INVALID_CURRENCY_MESSAGE = "Invalid Currency Type";

    private final String connectionUrl = "" +
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin,ethereum";
    private HttpURLConnection conn;


    public CurrencyInfo getCurrencyInfo(String currencyName) {
        return getRawMarketData().stream()
                .filter(currency -> currency.getSymbol().equalsIgnoreCase(currencyName))
                .findFirst().orElseThrow(() -> new InvalidCurrencyException(INVALID_CURRENCY_MESSAGE));
    }

    public List<CurrencyInfo> getRawMarketData() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL(connectionUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<List<CurrencyInfo>>() {
            }.getType();
            return gson.fromJson(responseContent.toString(), type);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }
}
