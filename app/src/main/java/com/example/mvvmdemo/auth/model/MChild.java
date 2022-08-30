package com.example.mvvmdemo.auth.model;

public class MChild {
    private int image;
    private String name;
    private int parentPosition;

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    public MChild(int image, String name, int parentPosition) {
        this.image = image;
        this.name = name;
        this.parentPosition =  parentPosition;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
