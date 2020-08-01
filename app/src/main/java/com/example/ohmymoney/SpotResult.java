package com.example.ohmymoney;

import io.realm.RealmObject;

public class SpotResult extends RealmObject{
    private String title;
    private String content;
    private String image;

    public SpotResult() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
