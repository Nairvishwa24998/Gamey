package com.shoppingwebsiteinterface.demo.dto;


import java.util.List;

public class GameOtherImagesDto {

    private List<String> otherImages;

    public GameOtherImagesDto(List<String> otherImages) {
        this.otherImages = otherImages;
    }

    public List<String> getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(List<String> otherImages) {
        this.otherImages = otherImages;
    }
}
