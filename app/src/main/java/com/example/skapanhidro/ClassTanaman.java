package com.example.skapanhidro;

public class ClassTanaman {
    String IdTanaman;
    String NamaTanaman;
    String FotoTanaman;
    String Masapanen;
    String JumlahTanaman;

    public ClassTanaman(String idTanaman, String namaTanaman, String fotoTanaman, String masapanen, String jumlahTanaman) {
        IdTanaman = idTanaman;
        NamaTanaman = namaTanaman;
        FotoTanaman = fotoTanaman;
        Masapanen = masapanen;
        JumlahTanaman = jumlahTanaman;
    }

    public String getIdTanaman() {
        return IdTanaman;
    }

    public void setIdTanaman(String idTanaman) {
        IdTanaman = idTanaman;
    }

    public String getNamaTanaman() {
        return NamaTanaman;
    }

    public void setNamaTanaman(String namaTanaman) {
        NamaTanaman = namaTanaman;
    }

    public String getFotoTanaman() {
        return FotoTanaman;
    }

    public void setFotoTanaman(String fotoTanaman) {
        FotoTanaman = fotoTanaman;
    }

    public String getMasapanen() {
        return Masapanen;
    }

    public void setMasapanen(String masapanen) {
        Masapanen = masapanen;
    }

    public String getJumlahTanaman() {
        return JumlahTanaman;
    }

    public void setJumlahTanaman(String jumlahTanaman) {
        JumlahTanaman = jumlahTanaman;
    }
}