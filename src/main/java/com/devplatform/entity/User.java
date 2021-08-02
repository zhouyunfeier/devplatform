package com.devplatform.entity;
import java.io.Serializable;

public class User implements Serializable {

    private String userid;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String avatar;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){this.userid = userid;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
