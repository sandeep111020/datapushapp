package com.example.datapushapp;

public class Vendoritemmodel {
    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String mname,number, desc, aname,artist,img,location;
    public Vendoritemmodel(){

    }
    public Vendoritemmodel(String mname, String desc, String number, String aname, String artist, String img, String location){
        this.desc=desc;
        this.mname=mname;
        this.number=number;
        this.aname=aname;
        this.artist=artist;
        this.location=location;
        this.img=img;

    }
}
