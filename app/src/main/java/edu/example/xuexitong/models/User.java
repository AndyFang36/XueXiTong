package edu.example.xuexitong.models;

import java.io.Serializable;

/**
 * 用户类
 */
public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
    private String gender;
    private String introduction;

    /**
     * 注册用
     */
    public User(String username, String password, String gender, String introduction) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.introduction = introduction;
    }

    public User(int userId, String username, String password, String gender, String introduction) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.introduction = introduction;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", gender='" + gender + '\'' +
            ", introduction='" + introduction + '\'' +
            '}';
    }
}
