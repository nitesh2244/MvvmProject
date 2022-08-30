package com.example.mvvmdemo.auth.model;

import java.io.Serializable;

public class MParent implements Serializable {

    private String Title;
    private String Heading;

    public MParent(String title, String heading) {
        Title = title;
        Heading = heading;
    }

    public String getTitle() {
        return Title;
    }

    public String getHeading() {
        return Heading;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }
}
