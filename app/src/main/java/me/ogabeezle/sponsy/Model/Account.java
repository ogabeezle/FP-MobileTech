package me.ogabeezle.sponsy.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URL;

import me.ogabeezle.sponsy.R;

public class Account {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("contactPerson")
    private String contactName;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("address")
    private String address;
    @SerializedName("deskripsi")
    private String deskripsi;
    private int picture=R.drawable.pasar_kreatif;

    public Account(){}

    public Account(int id, String name, String email, String contactName, String imageUrl, String address, String deskripsi, int picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactName = contactName;
        this.imageUrl = imageUrl;
        this.address = address;
        this.deskripsi = deskripsi;
//        this.picture=picture;
        this.picture= R.drawable.pasar_kreatif;
    }

    public Bitmap getBitmap() throws IOException {
        URL url = new URL(imageUrl);
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
