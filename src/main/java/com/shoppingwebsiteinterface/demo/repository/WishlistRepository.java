package com.shoppingwebsiteinterface.demo.repository;

import com.shoppingwebsiteinterface.demo.model.BasicGameInfo;
import com.shoppingwebsiteinterface.demo.model.UserInfo;
import com.shoppingwebsiteinterface.demo.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

    WishlistItem findByBasicGameInfoAndUser(BasicGameInfo basicGameInfo, UserInfo userInfo);
}
