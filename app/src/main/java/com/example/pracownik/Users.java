package com.example.pracownik;

public class Users {

    String firstName, email, passwd, userName;
    boolean is_user;


    public Users() {
    }

    public Users(String firstName, String email, String passwd, String userName, boolean is_user) {
        this.firstName = firstName;
        this.email = email;
        this.passwd = passwd;
        this.userName = userName;
        this.is_user=is_user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public boolean getIs_user() {
        return is_user;
    }

    public void setIs_user(boolean is_user) {
        this.is_user = is_user;
    }



}