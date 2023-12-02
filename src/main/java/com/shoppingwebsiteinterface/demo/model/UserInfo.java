package com.shoppingwebsiteinterface.demo.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String accountCreationDate;

    @OneToMany(mappedBy = "user")
    private Set<OwnedItem> ownedItems;

    @OneToMany(mappedBy = "user")
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "user")
    private Set<WishlistItem> wishlistItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<OwnedItem> getOwnedItems() {
        return ownedItems;
    }

    public void setOwnedItems(Set<OwnedItem> ownedItems) {
        this.ownedItems = ownedItems;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(Set<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}
