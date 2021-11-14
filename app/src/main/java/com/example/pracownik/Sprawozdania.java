package com.example.pracownik;

public class Sprawozdania {
    String nazwa_zadania, opis_zadania,miejsce, wykonawca, informacja, url;
    Double wynagrodzenie;

    public Sprawozdania() {
    }

    public Sprawozdania(String nazwa_zadania, String opis_zadania, String miejsce, String wykonawca, String informacja, String url, Double wynagrodzenie) {
        this.nazwa_zadania = nazwa_zadania;
        this.opis_zadania = opis_zadania;
        this.miejsce = miejsce;
        this.wykonawca = wykonawca;
        this.informacja = informacja;
        this.url = url;
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getNazwa_zadania() {
        return nazwa_zadania;
    }

    public String getOpis_zadania() {
        return opis_zadania;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public String getWykonawca() {
        return wykonawca;
    }

    public String getInformacja() {
        return informacja;
    }

    public String getUrl() {
        return url;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setNazwa_zadania(String nazwa_zadania) {
        this.nazwa_zadania = nazwa_zadania;
    }

    public void setOpis_zadania(String opis_zadania) {
        this.opis_zadania = opis_zadania;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public void setWykonawca(String wykonawca) {
        this.wykonawca = wykonawca;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }
}



