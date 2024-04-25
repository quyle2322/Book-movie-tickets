/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Ve;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author LENOVO
 */
public class VeDAO extends MainDAO<Ve, Integer>  {

    final String insert_SQL = "insert into Ve (mave, masuatchieu, maghe, thanhtien, khachhang, nhanvien,thanhtoan)"
            + "values (?,?,?,?,?,?,?)";
    final String update_SQL = "update Ve set thanhtien = ? where mave = ?";
    final String delete_SQL = "delete ve where maghe = ? and masuatchieu = ?";
    final String selectAll_SQL = "select * from Ve";
    final String selectById_SQL = "select * from Ve where maghe = ? and masuatchieu = ?";
    @Override
    public void Insert(Ve E) {
        JDBCHelper.Update(insert_SQL, E.getMaVe(), E.getMaSuatChieu(), E.getMaGhe(), 
                E.getThanhTien(), E.getEmailKH(), E.getEmailNV(),E.isThanhToan());
    }

    @Override
    public void Update(Ve E) {
         JDBCHelper.Update(update_SQL, E.getThanhTien(), E.getMaVe());
    }

    @Override
    public void Delete(Integer ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteGhe(String maGhe, Integer maSuatChieu)
    {
        JDBCHelper.Update(delete_SQL, maGhe,maSuatChieu);
    }

    @Override
    public List<Ve> SelectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public Ve SelectByMaGhe(String maGhe, Integer maSuatChieu) {
        List<Ve> listVe = selectBySQL(selectById_SQL,maGhe,maSuatChieu);
        if(listVe.isEmpty())
            return null;
        return listVe.get(0);
    }

    @Override
    public List<Ve> selectBySQL(String sql, Object... args) {
        List<Ve> listVe = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.Query(sql, args);
            while (rs.next()) {
                Ve v = new Ve();
                v.setMaVe(rs.getInt("mave"));
                v.setMaSuatChieu(rs.getInt("masuatchieu"));
                v.setMaGhe(rs.getString("maghe"));
                v.setThanhTien(rs.getFloat("thanhtien"));
                v.setEmailKH(rs.getString("khachhang"));
                v.setEmailNV(rs.getString("nhanvien"));
                v.setThanhToan(rs.getBoolean("thanhtoan"));
                listVe.add(v);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listVe;
    }

    @Override
    public Ve SelectById(Integer Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<Ve> selectBySC (int masc)
    {
        String sql = "select * from ve where masuatchieu = ? order by maghe";
        return this.selectBySQL(sql, masc);
    }
    
}
