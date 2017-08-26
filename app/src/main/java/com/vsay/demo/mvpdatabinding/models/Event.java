package com.vsay.demo.mvpdatabinding.models;

/**
 * Created by datct0407 on 10/6/15.
 */
public class Event {

    private String id;
    private String description;
    private String title;
    private String timestamp;
    private String imageURL;
    private String phone;
    private String date;
    private String locationLine1;
    private String locationLine2;

    public Event(String id, String description, String title, String timestamp, String imageURL, String phone, String date, String locationLine1, String locationLine2) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.timestamp = timestamp;
        this.imageURL = imageURL;
        this.phone = phone;
        this.date = date;
        this.locationLine1 = locationLine1;
        this.locationLine2 = locationLine2;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationLine1() {
        return locationLine1;
    }

    public void setLocationLine1(String locationLine1) {
        this.locationLine1 = locationLine1;
    }

    public String getLocationLine2() {
        return locationLine2;
    }

    public void setLocationLine2(String locationLine2) {
        this.locationLine2 = locationLine2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}