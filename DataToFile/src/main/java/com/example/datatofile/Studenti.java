package com.example.datatofile;

public class Studenti {

    private String nume,prenume;
    private String specialitatea = "Null";
    private int telefon;
    private int an_studiu;
    private double media;

public Studenti() {

}

public String getNume() {
        return nume;
}

public void setNume(String nume) {
        this.nume = nume;
}

public String getPrenume() {
        return prenume;
}

public void setPrenume(String prenume) {
        this.prenume = prenume;
}

public String getSpecialitatea() {
        return specialitatea;
}

public void setSpecialitatea(String specialitatea) {
        this.specialitatea = specialitatea;
}

public int getTelefon() {
        return telefon;
}

public void setTelefon(int telefon) {
        this.telefon = telefon;
}

public int getAn_studiu() {
        return an_studiu;
}

public void setAn_studiu(int an_studiu) {
        this.an_studiu = an_studiu;
}

public double getMedia() {
        return media;
}

public void setMedia(double media) {
        this.media = media;
}

public void setImportantData(String nume,String prenume){
        this.nume = nume;
        this.prenume = prenume;
}

@Override
public String toString() {
        return  nume + " " + prenume;
    }
}