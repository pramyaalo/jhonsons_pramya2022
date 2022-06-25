package com.triton.johnsonapp.activity;

public class SpinnerModel {
    private String storeroomname;
    private int id;

    public String getStoreroomname()
    {
        return storeroomname;
    }

    public void setStoreroomname(String storeroomname)
    {
        this.storeroomname=storeroomname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpinnerModel(String storeroomname) {
        this.storeroomname = storeroomname;

    }

}
