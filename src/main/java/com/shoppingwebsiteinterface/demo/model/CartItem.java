package com.shoppingwebsiteinterface.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    private Long gameId;

    private Integer quantity;


}
