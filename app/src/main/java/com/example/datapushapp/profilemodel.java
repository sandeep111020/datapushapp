package com.example.datapushapp;

public class profilemodel {
    String name,adress,city,img,lat,lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public profilemodel(){

    }
    public profilemodel(String name, String adress,String city,String img,String lat,String lon){
        this.adress=adress;
        this.city=city;
        this.img=img;
        this.name=name;
        this.lon=lon;
        this.lat=lat;
    }
}
