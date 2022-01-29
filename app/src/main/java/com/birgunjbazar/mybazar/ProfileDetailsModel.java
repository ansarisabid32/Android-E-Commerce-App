package com.birgunjbazar.mybazar;

public class ProfileDetailsModel {
    private String title,body,viewAll;

    public ProfileDetailsModel(String title, String body, String viewAll) {
        this.title = title;
        this.body = body;
        this.viewAll = viewAll;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getViewAll() {
        return viewAll;
    }

    public void setViewAll(String viewAll) {
        this.viewAll = viewAll;
    }
}
