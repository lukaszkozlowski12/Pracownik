package com.example.pracownik;

import java.time.LocalDate;
import java.util.Date;

public class Informacje {
    String notatka, tytul, userName;
    String data;
    public Informacje() {
    }
    public Informacje(String tytul,String notatka, String myObj, String userName) {
        this.notatka = notatka;
        this.tytul = tytul;
        this.data = myObj;
        this.userName=userName;
    }


    public String getNotatka() {
        return notatka;
    }

    public String getTytul() {
        return tytul;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getData() {
        return data;
    }

    public void setData(String myObj) {
        this.data = myObj;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


}
