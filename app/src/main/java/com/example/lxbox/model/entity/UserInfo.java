package com.example.lxbox.model.entity;

public class UserInfo {
    private int user_id;
    private String username;
    private String password;
    private int register_type;
    private int is_login;

    public static  UserInfo sUserInfo;

    public static UserInfo getUserInfo() {
        return sUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        sUserInfo = userInfo;
    }

    public UserInfo(int _id, String username, String password, int register_type) {
        this.user_id = _id;
        this.username = username;
        this.password = password;
        this.register_type = register_type;
    }


    public int get_id() {
        return user_id;
    }

    public void set_id(int _id) {
        this.user_id = _id;
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

    public int getRegister_type() {
        return register_type;
    }

    public void setRegister_type(int register_type) {
        this.register_type = register_type;
    }

    public int getIs_login() {
        return is_login;
    }

    public void setIs_login(int is_login) {
        this.is_login = is_login;
    }
}
