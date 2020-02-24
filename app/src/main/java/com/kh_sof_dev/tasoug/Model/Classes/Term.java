package com.kh_sof_dev.tasoug.Model.Classes;

public class Term {
    private String title,body,ID;

    public Term(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Term() {
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
