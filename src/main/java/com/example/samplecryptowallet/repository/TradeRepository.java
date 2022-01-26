package com.example.samplecryptowallet.repository;


import com.example.samplecryptowallet.model.Entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    Optional<List<Trade>> findAllByUserIdAndDateIsBetween(Long userId, Date startDate, Date endDate);

}
