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

    @ManyToOne
    @JoinColumn(name= "game_id")
    private BasicGameInfo basicGameInfo;

    private Integer quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public BasicGameInfo getBasicGameInfo() {
        return basicGameInfo;
    }

    public void setBasicGameInfo(BasicGameInfo basicGameInfo) {
        this.basicGameInfo = basicGameInfo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
