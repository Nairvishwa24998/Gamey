package com.shoppingwebsiteinterface.demo.repository;

import com.shoppingwebsiteinterface.demo.model.OwnedItem;
import com.shoppingwebsiteinterface.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedRepository extends JpaRepository<OwnedItem, Long> {
}
