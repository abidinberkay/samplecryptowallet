package com.example.samplecryptowallet.web;


import com.example.samplecryptowallet.Service.TradeService;
import com.example.samplecryptowallet.exception.UserNotFoundException;
import com.example.samplecryptowallet.model.Entity.Trade;
import com.example.samplecryptowallet.model.dto.TradeRequestDto;
import com.example.samplecryptowallet.model.dto.TradeResponseDto;
import com.example.samplecryptowallet.model.dto.TradeSummaryRequestDto;
import com.example.samplecryptowallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/baraka/trade")
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    UserRepository userRepository;

    private final String USER_ERROR_MSG = "User not found";

    @PostMapping("/dotrade")
    public TradeResponseDto trade(@RequestBody TradeRequestDto requestDto) throws Exception {
        userRepository.findById(requestDto.getUserId()).orElseThrow(()-> new UserNotFoundException(USER_ERROR_MSG));
        return tradeService.tradeCrypto(requestDto);
    }


    @PostMapping("/summary")
    public ResponseEntity<List<Trade>> getTradeList(@RequestBody TradeSummaryRequestDto requestDto) {
        userRepository.findById(requestDto.getUserId()).orElseThrow(()-> new UserNotFoundException(USER_ERROR_MSG));
        return ResponseEntity.ok(tradeService.getTradeHistory(requestDto));
    }

}
