package me.ogabeezle.sponsy.ui.home;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemCarouselModel {
    private int picture;
    private String title;
    private Date date;
    private String location;

    ItemCarouselModel(int picture,String title, Date date, String location){
        this.title=title;
        this.date=date;
        this.location=location;
        this.picture=picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPicture() {
        return picture;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateString(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String stringDate = dateFormat.format(date);
        if(stringDate.charAt(0)=='0'){
            return stringDate.substring(1);
        }
        return stringDate;
    }
}
