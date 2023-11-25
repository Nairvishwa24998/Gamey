package com.shoppingwebsiteinterface.demo.dto;


//GetAll GetAll GetAll  GetAll     GetAll            GetAll       GetClips GetId
//GameId Name,  Rating, Platforms, BackgroundImage,  ReleaseDate  Preview, Description
//id     name,  rating, platforms,   background_image, released     preview, description

public class GameInfoDto {

    private String id;


    private String name;


    private String description;


    private String rating;


    private String[] platforms;


    private String background_image;


    private String released;


    private String[] otherImages;

    public String[] getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(String[] otherImages) {
        this.otherImages = otherImages;
    }

    public GameInfoDto(String id, String name, String description, String rating, String[] platforms, String background_image, String released, String[] otherImages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.platforms = platforms;
        this.background_image = background_image;
        this.released = released;
        this.otherImages = otherImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }



}
