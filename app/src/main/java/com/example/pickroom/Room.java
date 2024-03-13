package com.example.pickroom;

public class Room {
    private String diachi;
    private String giathue;
    private String dientich;
    private String songuoi;
    private int hinh;
    private String tenphong;

    public Room(String diachi, String giathue, String dientich, String songuoi, int hinh, String tenphong) {
        this.diachi = diachi;
        this.giathue = giathue;
        this.dientich = dientich;
        this.songuoi = songuoi;
        this.hinh = hinh;
        this.tenphong = tenphong;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGiathue() {
        return giathue;
    }

    public void setGiathue(String giathue) {
        this.giathue = giathue;
    }

    public String getDientich() {
        return dientich;
    }

    public void setDientich(String dientich) {
        this.dientich = dientich;
    }

    public String getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(String songuoi) {
        this.songuoi = songuoi;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }
}
