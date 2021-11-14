package com.example.pracownik;

public class Zadania {
    String nazwa_zadania, opis_zadania, miejsce, wykonawca, data_publikacji, data_przyjecia, spr;
    Double wynagrodzenie;


    public Zadania() {
    }


    public Zadania(String nazwa_zadania, String opis_zadania, String miejsce, String wykonawca, String data_publikacji, String data_przyjecia, Double wynagrodzenie, String spr) {
        this.nazwa_zadania = nazwa_zadania;
        this.opis_zadania = opis_zadania;
        this.miejsce = miejsce;
        this.wykonawca = wykonawca;
        this.data_publikacji = data_publikacji;
        this.data_przyjecia = data_przyjecia;
        this.wynagrodzenie = wynagrodzenie;
        this.spr = spr;
    }






    public String getSpr() {
        return spr;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public String getNazwa_zadania() {
        return nazwa_zadania;
    }

    public String getOpis_zadania() {
        return opis_zadania;
    }

    public String getWykonawca() {
        return wykonawca;
    }

    public String getData_publikacji() {
        return data_publikacji;
    }

    public String getData_przyjecia() {
        return data_przyjecia;
    }

    public void setNazwa_zadania(String nazwa_zadania) {
        this.nazwa_zadania = nazwa_zadania;
    }

    public void setOpis_zadania(String opis_zadania) {
        this.opis_zadania = opis_zadania;
    }

    public void setWykonawca(String wykonawca) {
        this.wykonawca = wykonawca;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public void setData_publikacji(String data_publikacji) {
        this.data_publikacji = data_publikacji;
    }

    public void setData_przyjecia(String data_przyjecia) {
        this.data_przyjecia = data_przyjecia;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }
}
