package com.shoppingwebsiteinterface.demo.repository;

import com.shoppingwebsiteinterface.demo.model.BasicGameInfo;
import com.shoppingwebsiteinterface.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicGameInfoRepository extends JpaRepository<BasicGameInfo, Long> {

    BasicGameInfo findByRawgId(String rawgId);
}