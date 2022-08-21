package com.example.datapushapp;

public class Eventupoadmodel {
    String eventname;
    String eventdescription;

    public Eventupoadmodel(){

    }
    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdescription() {
        return eventdescription;
    }

    public void setEventdescription(String eventdescription) {
        this.eventdescription = eventdescription;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String eventlocation;
    String dateandtime;
    String img;
    public Eventupoadmodel(String eventname,String eventdescription,String eventlocation,String dateandtime,String img){
        this.eventdescription=eventdescription;
        this.eventlocation=eventlocation;
        this.eventname=eventname;
        this.img=img;
        this.dateandtime=dateandtime;
    }
}
