package com.shoppingwebsiteinterface.demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Basic Game-info")
public class BasicGameInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String rawgId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String background_image;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String[] platforms;

    @Column(nullable = false)
    private String released;

    @Column(nullable = false)
    private String[] otherImages;

    @OneToMany(mappedBy = "basicGameInfo")
    private Set<OwnedItem> ownedItems;

    @OneToMany(mappedBy = "basicGameInfo")
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "basicGameInfo")
    private Set<WishlistItem> wishlistItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRawgId() {
        return rawgId;
    }

    public void setRawgId(String rawgId) {
        this.rawgId = rawgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String[] getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(String[] otherImages) {
        this.otherImages = otherImages;
    }

    public Set<OwnedItem> getOwnedItems() {
        return ownedItems;
    }

    public void setOwnedItems(Set<OwnedItem> ownedItems) {
        this.ownedItems = ownedItems;
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
