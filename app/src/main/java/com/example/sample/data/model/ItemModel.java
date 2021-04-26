package com.example.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ItemModel implements Parcelable {
    private String id;
    private String description;
    private String title;
    private String timestamp;
    private String image;
    private String phone;
    private String date;
    @SerializedName("locationline1")
    private String location1;

    public ItemModel(){}

    protected ItemModel(Parcel in) {
        id = in.readString();
        description = in.readString();
        title = in.readString();
        timestamp = in.readString();
        image = in.readString();
        phone = in.readString();
        date = in.readString();
        location1 = in.readString();
        location2 = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(description);
        parcel.writeString(title);
        parcel.writeString(timestamp);
        parcel.writeString(image);
        parcel.writeString(phone);
        parcel.writeString(date);
        parcel.writeString(location1);
        parcel.writeString(location2);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    @SerializedName("locationline2")
    private String location2;
}
