package com.example.chat_app.Model;

public class UserProfileData {

    private String name;
    private String email;
    private String uuid;

    public UserProfileData() {
    }

    public UserProfileData(String name, String email, String uuid) {
        this.name = name;
        this.email = email;
        this.uuid = uuid;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
