package com.example.chat_app.Model;

public class users {
    private String name;
    private String email;
    private String uuid;
    private String uri;

    public users() {
    }

    public users(String name, String email, String uuid, String uri) {
        this.name = name;
        this.email = email;
        this.uuid = uuid;
        this.uri = uri;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
