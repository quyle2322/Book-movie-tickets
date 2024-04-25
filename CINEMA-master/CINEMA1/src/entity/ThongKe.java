/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Phat
 */
public class ThongKe {
    private String Ngay;
    private String Thang;
    private String Nam;
    private Integer slVe;
    private Integer Doanhthu;

    public ThongKe(String Ngay, String Thang, String Nam, int slVe, int Doanhthu) {
        this.Ngay = Ngay;
        this.Thang = Thang;
        this.Nam = Nam;
        this.slVe = slVe;
        this.Doanhthu = Doanhthu;
    }

    public ThongKe() {
    }
    
    
    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getThang() {
        return Thang;
    }

    public void setThang(String Thang) {
        this.Thang = Thang;
    }

    public String getNam() {
        return Nam;
    }

    public void setNam(String Nam) {
        this.Nam = Nam;
    }

    public int getSlVe() {
        return slVe;
    }

    public void setSlVe(int slVe) {
        this.slVe = slVe;
    }

    public int getDoanhthu() {
        return Doanhthu;
    }

    public void setDoanhthu(int Doanhthu) {
        this.Doanhthu = Doanhthu;
    }

    @Override
    public String toString() {
        return this.getNam();
    }
    public String toStringThang() {
        return this.getThang();
    }
    
}
