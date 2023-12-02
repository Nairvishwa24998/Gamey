package com.shoppingwebsiteinterface.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Wish-List")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name= "game_id")
    private BasicGameInfo basicGameInfo;

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
}
