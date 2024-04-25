/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import utils.Auth;

/**
 *
 * @author LENOVO
 */
public class Ve {
    private int vitri;
    private Integer maVe;
    private Integer maSuatChieu;
    private String maGhe;
    private double thanhTien;
    private String emailKH;
    private String emailNV;
    private boolean thanhToan;
    int tinhTrang = 0;

    public Integer getMaVe() {
        return maVe;
    }

    public void setMaVe(Integer maVe) {
        this.maVe = maVe;
    }

    public Integer getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(Integer maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String emailKH) {
        if (this.emailKH != null)
        {
            if (this.emailKH.equals(emailKH) && !this.isThanhToan())
                this.tinhTrang = 1;
            else
                this.tinhTrang = 2;
        }
        else
            this.tinhTrang = 2;
    }

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }
    
    

    
    @Override
    public String toString() {
        return "Ve{" + "maVe=" + maVe + ", maSuatChieu=" + maSuatChieu + ", maGhe=" + maGhe + ", thanhTien=" + thanhTien + ", emailKH=" + emailKH + ", emailNV=" + emailNV + '}';
    }
    
    
    
}
