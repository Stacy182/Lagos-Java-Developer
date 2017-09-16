package com.example.android.stacy;


public class getData {


    private String name, image ,gravatar , githuburl ,htmlurl;
    private int _id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int get_Id() {
        return _id;
    }

    public void set_Id(int ID) {
        this._id = ID;
    }

    public String getgravatar (){return gravatar;}

    public void setgravatar (String gravatar) {
        this.gravatar = gravatar;
    }


    public String geturl (){return githuburl;}

    public void seturl (String githuburl) {
        this.githuburl = githuburl;
    }

    public String gethtml (){return htmlurl;}

    public void sethtml (String htmlurl) {
        this.htmlurl = htmlurl;
    }




}