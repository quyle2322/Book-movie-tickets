/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Phim;
import entity.Phong;
import entity.SuatChieu;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author LENOVO
 */
public class SuatChieuDAO extends MainDAO<SuatChieu, Integer > {

    final String insert_SQL = "insert into SuatChieu (maphim, nguoitao, maphong, batdau, ketthuc, ngaychieu)"
            + " values (?,?,?,?,?,?) ";
    final String update_SQL = "update SuatChieu set batdau = ?, ketthuc = ?, ngaychieu = ? where masuatchieu = ?";
    
    final String delete_SQL = "DELETE FROM Suatchieu WHERE masuatchieu = ?";
    final String selectAll_SQL = "select * from suatchieu Order by ngaychieu desc";
    final String selectById_SQL = "select * from SuatChieu where masuatchieu= ?";
    final String selectByMaPhimMaPhong_SQL="SELECT * FROM SuatChieu  where maphong = ?";
    
    @Override
    public void Insert(SuatChieu E) {
        JDBCHelper.Update(insert_SQL, E.getMaSuatChieu(), E.getMaPhim(), E.getNguoiTao(), E.getMaPhong(), 
                E.getBatDau(), E.getKetThuc(), E.getNgayChieu());
    }

    @Override
    public void Update(SuatChieu E) {
        JDBCHelper.Update(update_SQL, E.getBatDau(), E.getKetThuc(), E.getNgayChieu(), E.getMaSuatChieu());
    }

    @Override
    public void Delete(Integer ID) {
        JDBCHelper.Update(delete_SQL, ID);
    }

    @Override
    public List<SuatChieu> SelectAll() {
        return selectBySQL(selectAll_SQL);
    }

    @Override
    public SuatChieu SelectById(Integer Id) {
        List<SuatChieu> listSC = new ArrayList<>();
        listSC = selectBySQL(selectById_SQL, Id);
        if(listSC.isEmpty()){
            return null;
        }
            
        return listSC.get(0);
    }
    
//    public SuatChieu selectByMaPhimMaPhong_SQL(String MaPhim,String MaPhong) {
//        List<SuatChieu> listSC = new ArrayList<>();
//        if(listSC.isEmpty())
//            return null;
//        return listSC.get(0);
//    }
    
    @Override
    public List<SuatChieu> selectBySQL(String sql, Object... args) {
        List<SuatChieu> listSC = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.Query(sql, args);
            while(rs.next())
            {
                SuatChieu sc = new SuatChieu();
                sc.setMaSuatChieu(rs.getInt("masuatchieu"));
                sc.setMaPhim(rs.getString("maphim"));
                sc.setNguoiTao(rs.getString("nguoitao"));
                sc.setMaPhong(rs.getString("maphong"));
                sc.setBatDau(rs.getString("batdau"));
                sc.setKetThuc(rs.getString("ketthuc"));
                sc.setNgayChieu(rs.getDate("ngaychieu"));
//                sc.setTrangThai(rs.getBoolean("trangthai"));
                listSC.add(sc);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return listSC;
    }

    public List<SuatChieu> selectByMaPhimMaPhong_SQL(String maPhim, String maphong) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM SuatChieu  where maphim = ? and maphong = ?";
        return this.selectBySQL(sql,maPhim,maphong);
    }

    public List<SuatChieu> selectByMaPhimMaPhong_SQL(Phong phg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM SuatChieu  where maphong = ?";
        return this.selectBySQL(sql,phg);
    }
    
}
