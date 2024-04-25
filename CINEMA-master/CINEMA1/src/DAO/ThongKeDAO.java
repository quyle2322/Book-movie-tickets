/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThongKe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author Phat
 */
public class ThongKeDAO{

    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for(int i=0;i<cols.length;i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
               list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getDoanhThuNgay() {
        String sql="{CALL sp_DoanhThuNgay}";
        String[] cols = {"Ngày chiếu","Số Lượng Vé","Doanh Thu"};
        return getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThuThang() {
        String sql="{CALL sp_DoanhThuThang}";
        String[] cols = {"thang","Số Lượng Vé","Doanh Thu"};
        return getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThuNhanVien() {
        String sql="{CALL sp_DoanhThuNhanVien}";
        String[] cols = {"nhanvien","SoVe","TongTien"};
        return getListOfArray(sql, cols);
    }
    public List<Object[]> gethotphim() {
        String sql="{CALL sp_hotphim}";
        String[] cols = {"maphim","tenphim","Số Lượng Vé"};
        return getListOfArray(sql, cols);
    }
}
