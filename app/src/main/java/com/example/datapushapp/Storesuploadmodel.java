package com.example.datapushapp;

public class Storesuploadmodel {
    String itemname,itemdesc,price,img;

    public String getItemname() {
        return itemname;
    }
    public Storesuploadmodel(){

    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Storesuploadmodel(String itemname, String itemdesc, String price, String img){
        this.img=img;
        this.itemdesc=itemdesc;
        this.itemname=itemname;
        this.price=price;

    }
}
