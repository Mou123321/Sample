package com.example.sample.data.local;

import com.example.sample.data.model.ItemModel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyItem")
public class ItemEntity {
    @NonNull
    @PrimaryKey
    String title;

    String id;
    String description;
    String timestamp;
    String phone;
    String date;
    String location1;
    String location2;

    String image;

    public ItemEntity() {}

    public ItemEntity(ItemModel itemModel) {
        title = itemModel.getTitle();
        id = itemModel.getId();
        description = itemModel.getDescription();
        timestamp = itemModel.getTimestamp();
        phone = itemModel.getPhone();
        date = itemModel.getDate();
        location1 = itemModel.getLocation1();
        location2 = itemModel.getLocation2();
        image = itemModel.getImage();
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
