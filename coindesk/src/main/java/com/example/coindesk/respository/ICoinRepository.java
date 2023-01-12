package com.example.coindesk.respository;

import com.example.coindesk.entity.CoinInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoinRepository extends JpaRepository<CoinInformation,Integer> {

}
