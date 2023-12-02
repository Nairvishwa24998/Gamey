package com.shoppingwebsiteinterface.demo.repository;

import com.shoppingwebsiteinterface.demo.model.BasicGameInfo;
import com.shoppingwebsiteinterface.demo.model.CartItem;
import com.shoppingwebsiteinterface.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByBasicGameInfoAndUser(BasicGameInfo basicGameInfo, UserInfo userInfo);

}
