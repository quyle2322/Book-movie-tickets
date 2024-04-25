/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLThongKe;

import DAO.ThongKeDAO;
import entity.SuatChieu;
import entity.ThongKe;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author Phat
 */
public class RunQLThongKe {
    int h = 500; int w = 800; 
    int ch = h/10;
    int cw = w/4;
    int x=10,y=10;
    
    JFrame  mainJFrame;
    JTabbedPane mainJTab;  
    JPanel NgayJPanel,ThangJPanel,NamJPanel,HotJPanel;
    JTable tblNgay,tblThang,tblNhanVien,tblHot;
    JComboBox cboNgay,cboThang,cboNam,cboHot;
    JLabel lblTitle,lblClose;
     
    public RunQLThongKe() {
        myGUI();
        filltotableNgay();
        filltotableThang();
        filltotableNhanVien();
        filltotablePhim();
    }
    
    private void myGUI(){
        mainJFrame = new JFrame("QLThongKe");        
        //mainJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainJFrame.setBounds(0, 0, w, h);
        mainJFrame.getContentPane().setBackground(new Color(255,240,235));
        mainJFrame.setLayout(null);
        
            lblTitle = new JLabel("Quản lý Thống kế");
            lblTitle.setFont( new Font("Arial",1,25));
            lblTitle.setForeground(java.awt.Color.pink);        
            lblTitle.setBounds(x, y, cw+50, ch);
            mainJFrame.add(lblTitle);
            
            lblClose = new JLabel("X");
            lblClose.setFont(new Font("Arial",1,30));
            lblClose.setBounds(w-60, y, 50, 50);
            lblClose.setCursor(new Cursor(12));
            lblClose.addMouseListener(new  MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    lblClose.setForeground(java.awt.Color.red);
                }
                public void mouseExited(MouseEvent e) {
                    lblClose.setForeground(java.awt.Color.black);
                }            
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });
            mainJFrame.add(lblClose);
                        
            y+=50;
            mainJTab = new JTabbedPane();
            mainJTab.setBounds(x, y, w-35, ch*9-20);       
            mainJTab.setBorder(new LineBorder(Color.red, 0, true));
            mainJTab.setBackground(new Color(255, 240, 235));
            
                NgayJPanel = new JPanel();
                NgayJPanel.setBackground(new Color(255, 240, 235));
                NgayJPanel.setName("DoanhThuNgay");
                                                           
                    String[] col = {"Ngày","Số lượng Vé","Doanh Thu"};            
                    DefaultTableModel model = new DefaultTableModel(col, 5);
                    tblNgay = new JTable(model);                    
                    JScrollPane scr_tkNgay = new JScrollPane(tblNgay);
                    scr_tkNgay.setPreferredSize(new Dimension(cw*4-50,ch*6+35));
                    scr_tkNgay.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    NgayJPanel.add(scr_tkNgay);                      
                                                            
                ThangJPanel = new JPanel();
                ThangJPanel.setBackground(new Color(255, 240, 235));
                ThangJPanel.setName("DoanhThuThang");
                                        
                    String[] col1 = {"Tháng","Số lượng vé","Doanh Thu"};            
                    model = new DefaultTableModel(col1, 5);
                    tblThang = new JTable(model);                    
                    JScrollPane scr_tkThang = new JScrollPane(tblThang);
                    scr_tkThang.setPreferredSize(new Dimension(cw*4-50,ch*6+35));
                    scr_tkThang.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    ThangJPanel.add(scr_tkThang);
                    
                NamJPanel = new JPanel();
                NamJPanel.setBackground(new Color(255, 240, 235));
                NamJPanel.setName("Nhan Vien");
                                                            
                    String[] col2 = {"Nhân Viên","Số lượng vé","Doanh Thu"};            
                    model = new DefaultTableModel(col2, 5);
                    tblNhanVien = new JTable(model);                    
                    JScrollPane scr_tkNam = new JScrollPane(tblNhanVien);
                    scr_tkNam.setPreferredSize(new Dimension(cw*4-50,ch*6+35));
                    scr_tkNam.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    NamJPanel.add(scr_tkNam);
                    
                HotJPanel = new JPanel();
                HotJPanel.setBackground(new Color(255, 240, 235));
                HotJPanel.setName("HotPhim");
                    
                    String[] col3 = {"Mã Phim","Tên Phim","Số lượng vé"};            
                    model = new DefaultTableModel(col2, 5);
                    tblHot = new JTable(model);                    
                    JScrollPane scr_tkPhim = new JScrollPane(tblHot);
                    scr_tkPhim.setPreferredSize(new Dimension(cw*4-50,ch*6+35));
                    scr_tkPhim.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    HotJPanel.add(scr_tkPhim);
                    
                mainJTab.add(NgayJPanel);
                mainJTab.add(ThangJPanel);
                mainJTab.add(NamJPanel);
                mainJTab.add(HotJPanel);
                
            mainJFrame.add(mainJTab);
        
        mainJFrame.setUndecorated(true);
        mainJFrame.setLocationRelativeTo(null); 
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new RunQLThongKe();
    }
    ThongKeDAO dao = new ThongKeDAO();
    
    void filltotableNgay(){
        DefaultTableModel model = (DefaultTableModel) tblNgay.getModel();
        model.setRowCount(0);
        
        List<Object[]> list = dao.getDoanhThuNgay();
        for(Object[] row : list){            
            model.addRow(row);
        }
    }
    
    void filltotableThang(){
        DefaultTableModel model = (DefaultTableModel) tblThang.getModel();
        model.setRowCount(0);
        
        List<Object[]> list = dao.getDoanhThuThang();
        for(Object[] row : list){            
            model.addRow(row);
        }
    }
    
    void filltotableNhanVien(){
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        //SuatChieu sc = (SuatChieu)cboNam.getSelectedItem();
        List<Object[]> list = dao.getDoanhThuNhanVien();
        for(Object[] row : list){            
            model.addRow(row);
        }
    }
    
    void filltotablePhim(){
        DefaultTableModel model = (DefaultTableModel) tblHot.getModel();
        model.setRowCount(0);
        //SuatChieu sc = (SuatChieu)cboNam.getSelectedItem();
        List<Object[]> list = dao.gethotphim();
        for(Object[] row : list){            
            model.addRow(row);
        }
    }
}
