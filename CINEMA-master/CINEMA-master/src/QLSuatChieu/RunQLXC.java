/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLSuatChieu;

import DAO.PhimDAO;
import DAO.PhongDAO;
import DAO.SuatChieuDAO;
import entity.Phim;
import entity.Phong;
import entity.SuatChieu;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;

/**
 *
 * @author Phat
 */
public class RunQLXC {
    int h = 500; int w = 800; 
    int ch = h/10;
    int cw = w/4;
    int x=10,y=10;
    JFrame  mainJFrame;
    JTabbedPane mainJTab;    
    JPanel XCJPanel,DSJPanel,CBOJpanel;
    JPanel mainJPanel, btnJPanel, TextJPanel, Text1JPanel;
    JLabel lblTitle,lblClose,lblMaPhim,lblNguoiTao,lblMaPhong,lblBatDau,lblKetThuc,lblNgayChieu,lblMASC;
    JTextField txtMaXC,txtMaPhim,txtNguoiTao,txtMaPhong,txtBatDau,txtKetThuc,txtNgayChieu,txtMASC;
    JButton btnThem,btnXoa,btnSua,btnMoi;
    JTable tblDSXC;
    JComboBox cboMaPhim,cboMaPhong;
    
    
    SuatChieuDAO dao = new SuatChieuDAO();
    PhimDAO phdao = new PhimDAO();
    PhongDAO phgdao = new PhongDAO();
    int row = -1;
    
    public RunQLXC() {
        myGUI();
        maChonPhim();
        maChonPhong();
    }
    
    private void myGUI(){
        mainJFrame = new JFrame("QLXC");
        //mainJFrame.setSize(w,h);
        //mainJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainJFrame.setBounds(0, 0, w, h);  
        //mainJFrame.setBackground(new Color(255, 240, 235));
        mainJFrame.getContentPane().setBackground(new Color(255,240,235));
        mainJFrame.setLayout(null);
        
        
        // CHU DE GIAO DIEN
        lblTitle = new JLabel("Quan ly Xuat Chieu");
        lblTitle.setFont( new Font("Arial",1,18));
        lblTitle.setForeground(java.awt.Color.pink);   
//        lblTitle.setBackground(Color.red);
//        lblTitle.setOpaque(true);
        lblTitle.setBounds(x, y, cw+50, ch-30);
        //lblTitle.setBackground(Color.green);
        //lblTitle.setOpaque(true);
        mainJFrame.add(lblTitle);
        
                
        CBOJpanel = new JPanel();
        CBOJpanel.setBounds(x, y+15, w-20, ch); 
        CBOJpanel.setBackground(new Color(255, 240, 235));
//        CBOJpanel.setBackground(Color.red);
//        CBOJpanel.setOpaque(true);
            cboMaPhim = new JComboBox();
            //cboMaPhim.setLayout(new FlowLayout(FlowLayout.LEFT));
            cboMaPhim.setFont(new Font("Arial",1,20));
            cboMaPhim.setPreferredSize(new Dimension(cw*2-20, ch-10));                    
            cboMaPhim.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            cboMaPhim.setBackground(new Color(255, 240, 235));
            //cboMaPhim.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                FilltocboPhim();  
                cboMaPhim.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        maChonPhim();
                    }
                });
            CBOJpanel.add(cboMaPhim);
            
            cboMaPhong = new JComboBox();
            //cboMaPhong.setLayout(new FlowLayout(FlowLayout.RIGHT));
            cboMaPhong.setFont(new Font("Arial",1,20));
            cboMaPhong.setPreferredSize(new Dimension(cw*2-20, ch-10));                    
            cboMaPhong.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            cboMaPhong.setBackground(new Color(255, 240, 235));
            //cboMaPhong.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                FilltocboPhong(); 
                cboMaPhong.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        maChonPhong();
                    }
                });
            CBOJpanel.add(cboMaPhong);
            
        mainJFrame.add(CBOJpanel);
        // Tabbed Panel
        y+=100;
        mainJTab = new JTabbedPane();
        mainJTab.setBounds(x, y-30, w-20, ch*7+10);       
        mainJTab.setBorder(new LineBorder(Color.red, 0, true));        
        mainJTab.setBackground(new Color(255, 240, 235));
        //mainJTab.set
            //TAB XUAT CHIEU
            XCJPanel = new JPanel();
            XCJPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            XCJPanel.setBackground(new Color(255, 240, 235));
            XCJPanel.setName("Suat Chieu");
                
                TextJPanel = new JPanel(new FlowLayout(0, 10, 30));
                TextJPanel.setPreferredSize(new Dimension(cw*2-20, ch*5));
                TextJPanel.setBackground(new Color(255, 240, 235));
                    
                    lblMaPhim = new JLabel("Ma Phim",JLabel.LEFT);
                    lblMaPhim.setPreferredSize(new Dimension(cw/2+10, ch-18)); 
                    //lblMaPhim.setBounds(0, 0, cw-30, ch-25);
                    lblMaPhim.setFont(new Font("Aial",1,18));   
                    lblMaPhim.setOpaque(true);
                    lblMaPhim.setBackground(new Color(255, 240, 235));
                    TextJPanel.add(lblMaPhim);
                    
                    txtMaPhim = new JTextField();
                    txtMaPhim.setFont(new Font("Aial",1,18));
                    txtMaPhim.setPreferredSize(new Dimension(cw+20, ch-18));
                    txtMaPhim.setEditable(false);
                    TextJPanel.add(txtMaPhim);
                    
                    lblMaPhong = new JLabel("Ma Phong",JLabel.LEFT);
                    lblMaPhong.setPreferredSize(new Dimension(cw/2+10, ch-18));                    
                    lblMaPhong.setFont(new Font("Aial",1,18));   
                    lblMaPhong.setOpaque(true);
                    lblMaPhong.setBackground(new Color(255, 240, 235));
                    TextJPanel.add(lblMaPhong);
                    
                    txtMaPhong = new JTextField();
                    txtMaPhong.setFont(new Font("Aial",1,18));
                    txtMaPhong.setPreferredSize(new Dimension(cw+20, ch-18));
                    txtMaPhong.setEditable(false);
                    TextJPanel.add(txtMaPhong);
                    
                    lblNguoiTao = new JLabel("Nguoi Tao",JLabel.LEFT);
                    lblNguoiTao.setPreferredSize(new Dimension(cw/2+10, ch-18));                    
                    lblNguoiTao.setFont(new Font("Aial",1,18));   
                    lblNguoiTao.setOpaque(true);
                    lblNguoiTao.setBackground(new Color(255, 240, 235));
                    TextJPanel.add(lblNguoiTao);
                    
                    txtNguoiTao = new JTextField();
                    txtNguoiTao.setFont(new Font("Aial",1,18));
                    txtNguoiTao.setPreferredSize(new Dimension(cw+20, ch-18));
                    txtNguoiTao.setEditable(false);
                    TextJPanel.add(txtNguoiTao);
                    
                    lblMASC = new JLabel("MA SC",JLabel.LEFT);
                    lblMASC.setPreferredSize(new Dimension(cw/2+10, ch-18));                    
                    lblMASC.setFont(new Font("Aial",1,18));   
                    lblMASC.setOpaque(true);
                    lblMASC.setBackground(new Color(255, 240, 235));
                    TextJPanel.add(lblMASC);
                    
                    txtMASC = new JTextField();
                    txtMASC.setFont(new Font("Aial",1,18));
                    txtMASC.setPreferredSize(new Dimension(cw+20, ch-18));
                    txtMASC.setEditable(false);
                    TextJPanel.add(txtMASC);
                    
                XCJPanel.add(TextJPanel);
                
                Text1JPanel = new JPanel(new FlowLayout(0, 10, 30));
                Text1JPanel.setPreferredSize(new Dimension(cw*2-20, ch*5));
                Text1JPanel.setBackground(new Color(255, 240, 235));
                
                    lblBatDau = new JLabel("Bat Dau",JLabel.LEFT);
                    lblBatDau.setPreferredSize(new Dimension(cw/2+10, ch-15));                    
                    lblBatDau.setFont(new Font("Aial",1,18));   
                    lblBatDau.setOpaque(true);
                    lblBatDau.setBackground(new Color(255, 240, 235));
                    Text1JPanel.add(lblBatDau);
                    
                    txtBatDau = new JTextField("08:00");
                    txtBatDau.setFont(new Font("Aial",1,18));
                    txtBatDau.setPreferredSize(new Dimension(cw+20, ch-15));
                    Text1JPanel.add(txtBatDau);
                    
                    lblKetThuc = new JLabel("Ket Thuc",JLabel.LEFT);
                    lblKetThuc.setPreferredSize(new Dimension(cw/2+10, ch-15));                    
                    lblKetThuc.setFont(new Font("Aial",1,18));   
                    lblKetThuc.setOpaque(true);
                    lblKetThuc.setBackground(new Color(255, 240, 235));
                    Text1JPanel.add(lblKetThuc);
                    
                    txtKetThuc = new JTextField();
                    txtKetThuc.setFont(new Font("Aial",1,18));
                    txtKetThuc.setPreferredSize(new Dimension(cw+20, ch-15));
                    Text1JPanel.add(txtKetThuc);
                    
                    lblNgayChieu = new JLabel("Ngay Chieu",JLabel.LEFT);
                    lblNgayChieu.setPreferredSize(new Dimension(cw/2+10, ch-15));                    
                    lblNgayChieu.setFont(new Font("Aial",1,18));   
                    lblNgayChieu.setOpaque(true);
                    lblNgayChieu.setBackground(new Color(255, 240, 235));
                    Text1JPanel.add(lblNgayChieu);
                    
                    txtNgayChieu = new JTextField();
                    txtNgayChieu.setFont(new Font("Aial",1,18));
                    txtNgayChieu.setPreferredSize(new Dimension(cw+20, ch-15));
                    Text1JPanel.add(txtNgayChieu);
                    
                XCJPanel.add(Text1JPanel);                    
               
                btnJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
                // mainJPanel.setBounds(x, y, cw*7, ch*5);
                btnJPanel.setPreferredSize(new Dimension(cw*4-70, ch+10));
                btnJPanel.setBackground(new Color(255, 240, 235));
                
                btnThem = new JButton("Them");
                btnThem.setPreferredSize(new Dimension(cw/2+40,ch-10));
                btnThem.setFont(new Font("Aial",1,20));   
                btnThem.setBackground(Color.pink);
                btnThem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        insert();
                    }
                });
                btnJPanel.add(btnThem);
                
                btnXoa = new JButton("Xoa");
                btnXoa.setPreferredSize(new Dimension(cw/2+40,ch-10));
                btnXoa.setFont(new Font("Aial",1,20));   
                btnXoa.setBackground(Color.pink);
                btnXoa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        delete();
                    }
                });
                btnJPanel.add(btnXoa);
                
                btnSua = new JButton("Sua");
                btnSua.setPreferredSize(new Dimension(cw/2+40,ch-10));
                btnSua.setFont(new Font("Aial",1,20));   
                btnSua.setBackground(Color.pink);
                btnSua.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        update();
                    }
                });
                btnJPanel.add(btnSua);
                
                btnMoi = new JButton("Moi");
                btnMoi.setPreferredSize(new Dimension(cw/2+40,ch-10));
                btnMoi.setFont(new Font("Aial",1,20)); 
                btnMoi.setBackground(Color.pink);
                btnMoi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        clearForm();
                    }
                });
                btnJPanel.add(btnMoi);
            
                XCJPanel.add(btnJPanel);


            //TAB DANH SACH
            
            DSJPanel = new JPanel();
            DSJPanel.setName("Danh Sach");
            DSJPanel.setBackground(new Color(255, 240, 235));
            
            String[] col = {"MaSC","Maphim","NguoiTao","MaPhong","BatDau","KetThuc","NgayChieu"};            
            DefaultTableModel model = new DefaultTableModel(col, 0);                
            tblDSXC = new JTable(model);   
            JScrollPane scr_xc = new JScrollPane(tblDSXC);
            scr_xc.setPreferredSize(new Dimension(cw*4-80,ch*7+7));
            DSJPanel.add(scr_xc);
            
                try {
                    List<SuatChieu> list = dao.SelectAll();
                    for(SuatChieu sc : list){
                        Object[] row = {
                            sc.getMaSuatChieu(),
                            sc.getMaPhim(),
                            sc.getNguoiTao(),                  
                            sc.getMaPhong(),
                            sc.getBatDau(),
                            sc.getKetThuc(),
                            sc.getNgayChieu()
                        };
                        model.addRow(row);// them 1 hang vao Jtable
                    }
                } catch (Exception e) {
                    System.out.println("loi truy van table");
                }
                
                
                tblDSXC.addMouseListener(new  MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        if(e.getClickCount() == 1){
                        row = tblDSXC.getSelectedRow();
                        edit();
        }
                    }
                });
                
            mainJTab.add(XCJPanel);
            mainJTab.add(DSJPanel);
        mainJFrame.add(mainJTab);
        
        // NUT THOAT
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("C",1,30));
        btnBack.setBounds(10, h-50, cw/2+40,ch-10);
        btnBack.setCursor(new Cursor(12));
        btnBack.addMouseListener(new  MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnBack.setForeground(java.awt.Color.red);
            }
            
            public void mouseExited(MouseEvent e) {
                btnBack.setForeground(java.awt.Color.black);
            }
            
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        mainJFrame.add(btnBack);
        
        mainJFrame.setUndecorated(true);
        mainJFrame.setLocationRelativeTo(null); 
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new RunQLXC();
    }
        
    void setForm(SuatChieu sc){
        txtMaPhim.setText(sc.getMaPhim());
        txtNguoiTao.setText(sc.getNguoiTao());
        txtMaPhong.setText(String.valueOf(sc.getMaPhong()));
        txtBatDau.setText(String.valueOf(sc.getBatDau()));
        txtKetThuc.setText(String.valueOf(sc.getBatDau()));    
        txtNgayChieu.setText(String.valueOf(sc.getNgayChieu()));       
        
        //txtMaXC.setText(String.valueOf(sc.getMaSuatChieu()));
        if (sc.getMaSuatChieu() != null) {
            txtMASC.setText(String.valueOf(sc.getMaSuatChieu()));
        } else {
            System.out.println("txtMaXC is null!");
        }
            }
    
    SuatChieu getForm(){
        SuatChieu sc = new SuatChieu();
        
        Integer Masc = (Integer) tblDSXC.getValueAt(row, 0);
        sc.setMaSuatChieu(Masc);
        
        sc.setMaPhim(txtMaPhim.getText());
        sc.setNguoiTao(txtNguoiTao.getText());
        sc.setMaPhong(txtMaPhong.getText());
        sc.setBatDau(txtBatDau.getText());
        sc.setKetThuc(txtKetThuc.getText());
        sc.setNgayChieu(XDate.toDate(txtNgayChieu.getText(),"yyyy-MM-dd"));
        
        return sc;      
    }    
    int thoiluong;
    void FilltocboPhim(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaPhim.getModel();
        model.removeAllElements();
        //String SQL = "SELECT maphim from Phim";
        List<Phim> list = phdao.SelectAll();
        for(Phim ph : list){
            model.addElement(ph);
        }       
    }
    
    void FilltocboPhong(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaPhong.getModel();
        model.removeAllElements();

        List<Phong> list = phgdao.SelectAll();
        for(Phong ph : list){
            model.addElement(ph);
            
        }
    }
    
    void fillTable(){
        DefaultTableModel model = (DefaultTableModel)tblDSXC.getModel();
        model.setRowCount(0);        
        try {
            Phong phg = (Phong)cboMaPhong.getSelectedItem();
            Phim phim = (Phim)cboMaPhim.getSelectedItem();
            List<SuatChieu> list = dao.selectByMaPhimMaPhong_SQL(phim.getMaPhim(),phg.getMaPhong());
            for(SuatChieu sc : list){
                Object[] row = {
                        sc.getMaSuatChieu(),
                        sc.getMaPhim(),
                        sc.getNguoiTao(),                  
                        sc.getMaPhong(),
                        sc.getBatDau(),
                        sc.getKetThuc(),
                        sc.getNgayChieu()
                };
                model.addRow(row);// them 1 hang vao Jtable
            }
            
        } catch (Exception e) {
            MsgBox.alert(null, "Lỗi Truy Vấn Dữ Liệu");
        }
        
    }
    
    void maChonPhim(){
        Phim p = (Phim) cboMaPhim.getSelectedItem();
        txtMaPhim.setText(p.getMaPhim());  
        int thoiluong = (int) p.getThoiLuong();
        addTime(thoiluong);
        fillTable();
    }
    
    void maChonPhong(){
        Phong phg = (Phong) cboMaPhong.getSelectedItem();
        txtMaPhong.setText(phg.getMaPhong());   
        fillTable();
    }
    
    void clearForm(){
        SuatChieu sc = new SuatChieu();        
        this.setForm(sc);
        this.row = -1; 
               
        txtNguoiTao.setText(String.valueOf(Auth.user.getTenNV()));
        txtBatDau.setText("");
        txtKetThuc.setText("");
        txtNgayChieu.setText("");
        int newMasc = generateNewMasc();
        txtMASC.setText(String.valueOf(newMasc));
    }
    
    int generateNewMasc() {
        DefaultTableModel model = (DefaultTableModel) tblDSXC.getModel();
        int rowCount = model.getRowCount();

        if (rowCount > 0) {
            int maxMasc = (int) model.getValueAt(0, 0);

            for (int i = 1; i < rowCount; i++) {
                int currentMasc = (int) model.getValueAt(i, 0);
                if (currentMasc > maxMasc) {
                    maxMasc = currentMasc;
                }
            }
            return maxMasc + 1;
        } else {            
            return 1;
        }
    }
    
    void insert(){
        SuatChieu sc = this.getForm();
        try {
                dao.Insert(sc);                
                this.clearForm();
                fillTable();
                MsgBox.alert(null, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(null, "Thêm mới thất bại!");
                
            }        
    }
    
    void update(){
        SuatChieu sc = this.getForm(); 
        try {           
            dao.Update(sc);
            fillTable();
            MsgBox.alert(null, "cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(null, "cập nhật thất bại");
        }        
    }
    
    void delete(){
        
        if(!Auth.isManager()){
            MsgBox.alert(null, "Ban ko co quyen");
        }
        else{
            //KhoaHoc kh = this.getForm();  
            Integer Masc = (Integer) tblDSXC.getValueAt(row, 0);
            //kh.setMaKH(Makh);
            if(MsgBox.confirm(null, "Bạn thức sự muốn xóa ")){
                    try {
                        dao.Delete(Masc);
                        this.clearForm();
                        fillTable();
                        MsgBox.alert(null, "Xóa thành công!");
                    } catch (Exception e) {
                        MsgBox.alert(null, "Xóa thất bại!");
                    }
            }                      
        }
    }
    
    private void addTime(int thoiluong) {
        String inputTime = txtBatDau.getText();     
        
        try {
            // Chuyển đổi chuỗi thời gian thành LocalTime
            LocalTime parsedTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));
                        
            // Cộng thêm phút
            LocalTime resultTime = parsedTime.plusMinutes(thoiluong+30);

            // Hiển thị kết quả
            txtKetThuc.setText(resultTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            
        } catch (Exception e) {
             txtKetThuc.setText("Invalid time format");
        }
    }
    
        void edit(){
            Object masc = tblDSXC.getValueAt(this.row, 0);
            int manv = (int)masc;
            SuatChieu sc = dao.SelectById(manv);
            sc.setMaSuatChieu(manv);
            this.setForm(sc);
            mainJTab.setSelectedIndex(0);
        }

}
