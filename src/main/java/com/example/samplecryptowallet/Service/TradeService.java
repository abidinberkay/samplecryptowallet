package com.example.samplecryptowallet.Service;

import com.example.samplecryptowallet.exception.InsufficientBalanceException;
import com.example.samplecryptowallet.exception.NoAvailableAccountException;
import com.example.samplecryptowallet.model.Entity.Account;
import com.example.samplecryptowallet.model.Entity.Trade;
import com.example.samplecryptowallet.model.Pojo.CurrencyInfo;
import com.example.samplecryptowallet.model.dto.TradeRequestDto;
import com.example.samplecryptowallet.model.dto.TradeResponseDto;
import com.example.samplecryptowallet.model.dto.TradeSummaryRequestDto;
import com.example.samplecryptowallet.model.enums.Currency;
import com.example.samplecryptowallet.model.enums.OperationType;
import com.example.samplecryptowallet.repository.AccountRepository;
import com.example.samplecryptowallet.repository.TradeRepository;
import com.example.samplecryptowallet.util.Mapper;
import com.example.samplecryptowallet.util.MarketDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    Mapper mapper;

    @Autowired
    MarketDataFetcher marketFetcher;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TradeRepository tradeRepository;


    private final String BUY = OperationType.BUY.name();
    private final String SELL = OperationType.SELL.name();
    private final String ACCOUNT_ERROR_MSG = "User has no available account";
    private final String TRANSFER_SUCCESS_MESSAGE = "Transfer is done successfully";
    private final String TRANSFER_FAIL_MESSAGE = "Insufficient balance";

    public TradeResponseDto tradeCrypto(TradeRequestDto requestDto) throws Exception {
        List<Account> accountList = accountRepository.getAccountsByUserId(requestDto.getUserId());
        Optional<Account> usdtAccount = accountList.stream()
                .filter(account -> account.getAccountCurrencyType().equalsIgnoreCase(Currency.USDT.toString()))
                .findFirst();
        Optional<Account> cryptoAccount = accountList.stream()
                .filter(account -> account.getAccountCurrencyType().equalsIgnoreCase(requestDto.getCurrency()))
                .findFirst();

        CurrencyInfo currencyInfo = marketFetcher.getCurrencyInfo(requestDto.getCurrency());
        BigDecimal cryptoPrice = BigDecimal.valueOf(Double.parseDouble(currencyInfo.getCurrent_price()));

        if (cryptoAccount.isEmpty()) {
            cryptoAccount = Optional.of(createAccount(requestDto));
            accountList.add(cryptoAccount.orElseThrow(null));
        }

        if (isBalanceAvailableForTrade(requestDto, accountList, cryptoPrice)) {
            return doTrade(requestDto, usdtAccount.orElseThrow(Exception::new), cryptoAccount.orElseThrow(Exception::new), cryptoPrice);
        }

        return TradeResponseDto.builder()
                .userId(requestDto.getUserId())
                .cryptoCurrencyType(requestDto.getCurrency())
                .tradeResultMessage(TRANSFER_FAIL_MESSAGE)
                .build();
    }

    private TradeResponseDto doTrade(TradeRequestDto requestDto,
                                     Account usdtAccount,
                                     Account cryptoAccount,
                                     BigDecimal cryptoPrice) {

        BigDecimal usdtChangeAmount = requestDto.getAmount().multiply(cryptoPrice);

        BigDecimal usdtBalanceBeforeTrade = usdtAccount.getAmount();
        BigDecimal cryptoBalanceBeforeTrade = cryptoAccount.getAmount();

        if (requestDto.getOperationType().equalsIgnoreCase(BUY)) {
            usdtAccount.setAmount(usdtAccount.getAmount().subtract(usdtChangeAmount));
            cryptoAccount.setAmount(cryptoAccount.getAmount().add(requestDto.getAmount()));
        } else {
            usdtAccount.setAmount(usdtAccount.getAmount().add(usdtChangeAmount));
            cryptoAccount.setAmount(cryptoAccount.getAmount().subtract(requestDto.getAmount()));
        }
        usdtAccount = accountRepository.save(usdtAccount);
        cryptoAccount = accountRepository.save(cryptoAccount);

        Date tradeDate = new Date();
        tradeRepository.save(Trade.builder()
                .userId(requestDto.getUserId())
                .cryptoName(requestDto.getCurrency())
                .currencyDollarValue(cryptoPrice)
                .tradeType(requestDto.getOperationType().toUpperCase())
                .usdtBalanceBeforeTrade(usdtBalanceBeforeTrade)
                .usdtBalanceAfterTrade(usdtAccount.getAmount())
                .cryptoBalanceBeforeTrade(cryptoBalanceBeforeTrade)
                .cryptoBalanceAfterTrade(cryptoAccount.getAmount())
                .amount(requestDto.getAmount())
                .date(tradeDate)
                .build());

        return TradeResponseDto.builder()
                .userId(requestDto.getUserId())
                .newUsdtBalance(usdtAccount.getAmount())
                .newCryptoBalance(cryptoAccount.getAmount())
                .cryptoCurrencyType(requestDto.getCurrency())
                .tradeDate(tradeDate)
                .tradeResultMessage(TRANSFER_SUCCESS_MESSAGE)
                .build();
    }

    private boolean isBalanceAvailableForTrade(TradeRequestDto requestDto, List<Account> accountList, BigDecimal cryptoPrice)
            throws InsufficientBalanceException {
        BigDecimal dollarAccAmount = getAmount(accountList, Currency.USDT.toString());
        BigDecimal cryptoAccAmount = getAmount(accountList, requestDto.getCurrency());

        BigDecimal cryptoDollarValue = requestDto.getAmount().multiply(cryptoPrice);


        if (requestDto.getOperationType().equalsIgnoreCase(BUY)) {
            if (dollarAccAmount.compareTo(cryptoDollarValue) >= 0) {
                return true;
            }
            throw new InsufficientBalanceException(TRANSFER_FAIL_MESSAGE);
        }
        if (requestDto.getOperationType().equalsIgnoreCase(SELL)) {
            if (cryptoAccAmount.compareTo(requestDto.getAmount()) >= 0) {
                return true;
            }
            throw new InsufficientBalanceException(TRANSFER_FAIL_MESSAGE);
        }
        return false;
    }

    private BigDecimal getAmount(List<Account> accountList, String currency) {
        return accountList.stream()
                .filter(Objects::nonNull)
                .filter(account -> account.getAccountCurrencyType().equalsIgnoreCase(currency))
                .findFirst()
                .map(Account::getAmount)
                .orElseThrow(() -> new NoAvailableAccountException(ACCOUNT_ERROR_MSG));
    }

    private Account createAccount(TradeRequestDto requestDto) {
        Account newAccount = new Account();
        newAccount.setUserId(requestDto.getUserId());
        newAccount.setAmount(BigDecimal.valueOf(0));
        newAccount.setAccountCurrencyType(requestDto.getCurrency().toUpperCase());

        return accountRepository.save(newAccount);
    }

    public List<Trade> getTradeHistory(TradeSummaryRequestDto dto) {
        return tradeRepository.findAllByUserIdAndDateIsBetween(dto.getUserId(), dto.getStartDate(), dto.getEndDate())
                .orElseThrow(null);
    }
}
