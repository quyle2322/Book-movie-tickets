/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyNhanSu;

import DAO.NhanVienDAO;
//import com.sun.javafx.iio.common.ImageTools;
import entity.NhanVien;
import entity.Phim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Auth;
import utils.MsgBox;
import utils.XImage;

/**
 *
 * @author LENOVO
 */
public class quanLiNhanSu {

    int w = 1000, h = 950;
    int cw = w / 10, ch = h / 10;
    int x = 10, y = 10;

    DefaultTableModel model;
    NhanVienDAO nvdao = new NhanVienDAO();
    Object[] columns = {"Email", "Mat Khau", "TenNV", "SoDT", "NgaySinh", "GioiTinh", "DiaChi", "VaiTro", "Hinh", "Trang Thai"};
    Object data[][];
    int row = -1;

    JFileChooser fileChooser = new JFileChooser();
    JFrame mainFrame;
    JTabbedPane mainTab;
    JPanel NhanSu, DanhSach, pnlTT, pnlChucNang, pnlLable, pnlTextfield, pnlGioiTinh, pnlChucVu, pnlHinh, pnlQuayLai, pnlXuatDS, pnlMove;
    JTable tblDanhSach;
    JLabel lblemailNV, lblmatKhau, lblhoTen, lblsoDT, lblngaySinh, lblGioiTinh, lblDiaChi, lblChucVu, lblHinh;
    JTextField txtEmail, txtHoTen, txtSoDT, txtngaySinh, txtDiaChi, txtTrangThai;
    JPasswordField txtMatKhau;
    JRadioButton rdoNam, rdoNu, rdoTruongPhong, rdoNhanVien, rdoOn, rdoOff;
    ButtonGroup btngGioiTinh, btngVaiTro, btngTrangThai;
    JButton btnThem, btnXoa, btnSua, btnMoi, btnQuayLai, btnFirst, btnPre, btnNext, btnLast, btnXuatDS;

    public quanLiNhanSu() {
        myGui();
    }

    @SuppressWarnings("empty-statement")
    private void myGui() {
        mainFrame = new JFrame("Quan Li Nhan Su");
        mainFrame.setSize(w, h);
        mainFrame.setLayout(null);
//        mainFrame.setUndecorated(true);
        mainFrame.setDefaultCloseOperation(new JFrame().EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        y += 10;
        mainTab = new JTabbedPane();
        mainTab.setBounds(x, y, cw * 10 - 40, ch * 10 - 40);
        mainTab.setFont(new Font("Source Sans Pro", 1, 25));
        y += 100;
        NhanSu = new JPanel();
        NhanSu.setLayout(new FlowLayout());
        NhanSu.setBounds(x, y, cw * 10, ch * 10);

        pnlLable = new JPanel(new GridLayout(10, 1, 10, 10));
        pnlLable.setPreferredSize(new Dimension(cw, ch * 6+70));
//        pnlLable.setBorder(new LineBorder(Color.black, 1, true));

        pnlTextfield = new JPanel(new GridLayout(10, 1, 10, 10));
        pnlTextfield.setPreferredSize(new Dimension(cw * 5, ch * 6+70));
//        pnlTextfield.setBorder(new LineBorder(Color.black, 1, true));
        
        pnlGioiTinh = new JPanel(new GridLayout(1, 2));
        pnlGioiTinh.setPreferredSize(new Dimension(cw * 7, ch));
//        pnlGioiTinh.setBorder(new LineBorder(Color.black, 1, true));

        pnlChucVu = new JPanel(new GridLayout(1, 2));
        pnlChucVu.setPreferredSize(new Dimension(cw * 7, ch));

        pnlHinh = new JPanel(new GridLayout(1,1));
        pnlHinh.setPreferredSize(new Dimension(cw * 3+20, ch * 5));
        pnlHinh.setBorder(new LineBorder(Color.black, 2, true));

        pnlQuayLai = new JPanel(new GridLayout(1, 1));
        pnlQuayLai.setPreferredSize(new Dimension(cw * 3, ch-50 ));
//        pnlQuayLai.setBorder(new LineBorder(Color.black, 2, true));

        pnlXuatDS = new JPanel(new GridLayout(1, 1));
        pnlXuatDS.setPreferredSize(new Dimension(cw * 5, ch - 30));
        pnlXuatDS.setBorder(new LineBorder(Color.black, 2, true));
        
        pnlMove = new JPanel(new GridLayout(1, 1));
        pnlMove.setPreferredSize(new Dimension(cw * 5, ch +60));
//        pnlMove.setBorder(new LineBorder(Color.black, 2, true));

        pnlTT = new JPanel();

//        pnlTT.setLayout(new GridLayout(10, 0, 0, 5));
        pnlTT.setPreferredSize(new Dimension(cw * 9 + 50, ch * 6+90));
//        pnlTT.setBorder(new LineBorder(Color.black, 1, true));
//        pnlTT.setBackground(new Color(255, 240, 235));
        pnlTT.add(pnlLable);
        pnlTT.add(pnlTextfield);
        pnlTT.add(pnlHinh);
        

        y += 10;
        lblemailNV = new JLabel("Email: ");
        lblemailNV.setPreferredSize(new Dimension(cw / 2, ch / 2));
//        lblemailNV.setBounds(x, y, cw, ch);
        lblemailNV.setFont(new Font("Source Sans Pro", 3, 20));
        lblemailNV.setOpaque(true);
//        lblemailNV.setBackground(new Color(255, 240, 235));
//        lblemailNV.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblemailNV);

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(cw * 7 + 83, ch - 50));
        txtEmail.setFont(new Font("Source Sans Pro", 3, 20));

//        txtEmail.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe)
//            {
//                txtEmail.setText("");
//            }
//            public void focusLost(FocusEvent fe) {
//                if (txtEmail.getText().equals("")) {
//                    txtEmail.setText("Nhập email");
//                }
//            }
//        });
        pnlTextfield.add(txtEmail);

        y += 10;
        lblmatKhau = new JLabel("Mat Khau: ");
        lblmatKhau.setPreferredSize(new Dimension(cw / 2 + 50, ch / 2));
//        lblmatKhau.setBounds(x, y, cw+100, ch );
        lblmatKhau.setFont(new Font("Source Sans Pro", 3, 20));
        lblmatKhau.setOpaque(true);
//        lblmatKhau.setBackground(new Color(255, 240, 235));
//        lblmatKhau.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblmatKhau);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setPreferredSize(new Dimension(cw * 7 + 50, ch - 50));
        txtMatKhau.setFont(new Font("Source Sans Pro", 3, 20));

//        txtMatKhau.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe) {
//                txtMatKhau.setText("");
//            }
//
//            public void focusLost(FocusEvent fe) {
//                if (txtMatKhau.getPassword().equals("")) {
//                    txtMatKhau.setToolTipText("123456");
//                }
//            }
//        });
        pnlTextfield.add(txtMatKhau);

        y += 10;
        lblhoTen = new JLabel("TenNV: ");
//        lblhoTen.setPreferredSize(new Dimension(cw , ch));
        lblhoTen.setBounds(x, y, cw + 100, ch);
        lblhoTen.setFont(new Font("Source Sans Pro", 3, 20));
        lblhoTen.setOpaque(true);
//        lblhoTen.setBackground(new Color(255, 240, 235));
//        lblhoTen.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblhoTen);

        txtHoTen = new JTextField();
        txtHoTen.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtHoTen.setFont(new Font("Source Sans Pro", 3, 20));
//        txtHoTen.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe) {
//                txtHoTen.setText("");
//            }
//            public void focusLost(FocusEvent fe) {
//                if (txtHoTen.getText().equals("")) {
//                    txtHoTen.setText("Nhap HoTen");
//                }
//            }
//        });

        pnlTextfield.add(txtHoTen);

        lblsoDT = new JLabel("Số ĐT: ");
        lblsoDT.setPreferredSize(new Dimension(cw, ch));
        lblsoDT.setFont(new Font("Source Sans Pro", 3, 20));
        lblsoDT.setOpaque(true);
//        lblsoDT.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblsoDT);

        txtSoDT = new JTextField();
        txtSoDT.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtSoDT.setFont(new Font("Source Sans Pro", 3, 20));
//        txtSoDT.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe) {
//                txtSoDT.setText("");
//            }
//
//            public void focusLost(FocusEvent fe) {
//                if (txtSoDT.getText().equals("")) {
//                    txtSoDT.setText("Nhập Số ĐT");
//                }
//            }
//        });

        pnlTextfield.add(txtSoDT);

        lblngaySinh = new JLabel("Ngày Sinh: ");
        lblngaySinh.setPreferredSize(new Dimension(cw, ch));
        lblngaySinh.setFont(new Font("Source Sans Pro", 3, 20));
        lblngaySinh.setOpaque(true);
//        lblngaySinh.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblngaySinh);

        txtngaySinh = new JTextField();
        txtngaySinh.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtngaySinh.setFont(new Font("Source Sans Pro", 3, 20));
//        txtngaySinh.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe) {
//                txtngaySinh.setText("");
//            }
//
//            public void focusLost(FocusEvent fe) {
//                if (txtngaySinh.getText().equals("")) {
//                    txtngaySinh.setText("Nhập Ngày Sinh");
//                }
//            }
//        });
        pnlTextfield.add(txtngaySinh);

        lblGioiTinh = new JLabel("Giới Tính: ");
//        lblGioiTinh.setBounds(x+10, y+10, cw+25, ch-30);
        lblGioiTinh.setPreferredSize(new Dimension(cw, ch));
        lblGioiTinh.setFont(new Font("Source Sans Pro", 3, 20));
        lblGioiTinh.setOpaque(true);
//        lblGioiTinh.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblGioiTinh);

        rdoNam = new JRadioButton("Nam");
        rdoNam.setPreferredSize(new Dimension(cw, ch));
        rdoNam.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNam.setBackground(new Color(255, 240, 235));
        rdoNam.setSelected(true);
        pnlGioiTinh.add(rdoNam);

        rdoNu = new JRadioButton("Nữ");
        rdoNu.setPreferredSize(new Dimension(cw, ch));
        rdoNu.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlGioiTinh.add(rdoNu);
        pnlTextfield.add(pnlGioiTinh);

        btngGioiTinh = new ButtonGroup();
        btngGioiTinh.add(rdoNam);
        btngGioiTinh.add(rdoNu);

        lblDiaChi = new JLabel("Địa Chỉ: ");
        lblDiaChi.setPreferredSize(new Dimension(cw, ch));
        lblDiaChi.setFont(new Font("Source Sans Pro", 3, 20));
        lblDiaChi.setOpaque(true);
//        lblDiaChi.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblDiaChi);

        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtDiaChi.setFont(new Font("Source Sans Pro", 3, 20));
//        txtDiaChi.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent fe) {
//                txtDiaChi.setText("");
//            }
//
//            public void focusLost(FocusEvent fe) {
//                if (txtDiaChi.getText().equals("")) {
//                    txtDiaChi.setText("Nhập Địa Chỉ NV");
//                }
//            }
//        });
        pnlTextfield.add(txtDiaChi);

        lblChucVu = new JLabel("Chức Vụ: ");
//        lblGioiTinh.setBounds(x+10, y+10, cw+25, ch-30);
        lblChucVu.setPreferredSize(new Dimension(cw, ch));
        lblChucVu.setFont(new Font("Source Sans Pro", 3, 20));
        lblChucVu.setOpaque(true);
//        lblChucVu.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblChucVu);

        rdoTruongPhong = new JRadioButton("Trưởng Phòng");
        rdoTruongPhong.setPreferredSize(new Dimension(cw + 90, ch - 30));
        rdoTruongPhong.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoTruongPhong.setBackground(new Color(255, 240, 235));
        pnlChucVu.add(rdoTruongPhong);

        rdoNhanVien = new JRadioButton("Nhân Viên");
        rdoNhanVien.setPreferredSize(new Dimension(cw * 6, ch - 30));
        rdoNhanVien.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNhanVien.setBackground(new Color(255, 240, 235));
        rdoNhanVien.setSelected(true);
        pnlChucVu.add(rdoNhanVien);
        pnlTextfield.add(pnlChucVu);

        btngVaiTro = new ButtonGroup();
        btngVaiTro.add(rdoTruongPhong);
        btngVaiTro.add(rdoNhanVien);

        lblHinh = new JLabel("Hinh", JLabel.CENTER);
        lblHinh.setPreferredSize(new Dimension(cw * 3-10, ch * 5+30));
        lblHinh.setFont(new Font("Source Sans Pro", 3, 20));
        lblHinh.setOpaque(true);
//        lblHinh.setBackground(new Color(255, 240, 235));
        lblHinh.setBorder(new LineBorder(Color.black, 1, true));
        lblHinh.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                lblHinh.setText("");
                chonAnh();
            }
        });
        pnlHinh.add(lblHinh);

        txtTrangThai = new JTextField("Trang Thai");
        txtTrangThai.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtTrangThai.setFont(new Font("Source Sans Pro", 3, 20));
        txtTrangThai.setEnabled(false);
        pnlTextfield.add(txtTrangThai);

        NhanSu.add(pnlTT);

        pnlChucNang = new JPanel();
        pnlChucNang.setPreferredSize(new Dimension(cw * 9, ch ));
//        pnlChucNang.setBackground(new Color(255, 240, 235));
//        pnlChucNang.setBorder(new LineBorder(Color.black, 1, true));
        pnlChucNang.setLayout(new GridLayout(2, 0, 5, 5));

        // Button Them
        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/icon/Add.png"));
        btnThem = new JButton("Add",iconAdd);
        btnThem.setPreferredSize(new Dimension(cw + 100, ch - 50));
        btnThem.setFont(new Font("Source Sans Pro", 3, 20));
//        btnThem.setBackground(new Color(255, 240, 235));
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                insert();
            }
        });
        pnlChucNang.add(btnThem);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/icon/Delete.png"));
        btnXoa = new JButton("Delete",iconDelete);
        btnXoa.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnXoa.setFont(new Font("Source Sans Pro", 3, 20));
//                btnXoa.setBackground(new Color(255, 240, 235));
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        pnlChucNang.add(btnXoa);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/icon/Update.png"));
        btnSua = new JButton("Update",iconUpdate);
        btnSua.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnSua.setFont(new Font("Aial", 1, 20));
//                btnSua.setBackground(new Color(255, 240, 235));
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        pnlChucNang.add(btnSua);

        ImageIcon iconClear = new ImageIcon(getClass().getResource("/icon/Clear.png"));
        btnMoi = new JButton("Clear",iconClear);
        btnMoi.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnMoi.setFont(new Font("Source Sans Pro", 3, 20));
//                btnMoi.setBackground(new Color(255, 240, 235));
        btnMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                btnThem.setEnabled(true);
                btnXoa.setEnabled(true);
                btnSua.setEnabled(true);
                btnMoi.setEnabled(false);
            }
        });
        pnlChucNang.add(btnMoi);
        ImageIcon first = new ImageIcon(getClass().getResource("/icon/first.png"));
        System.out.println(first);
        btnFirst = new JButton(first);
        btnFirst.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnFirst.setFont(new Font("Source Sans Pro", 3, 20));
//                btnFirst.setBackground(new Color(255, 240, 235));
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dau();
            }
        });
        pnlMove.add(btnFirst);

        ImageIcon previous = new ImageIcon(getClass().getResource("/icon/previous.png"));
        btnPre = new JButton(previous);
        btnPre.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnPre.setFont(new Font("Source Sans Pro", 3, 20));
//                btnPre.setBackground(new Color(255, 240, 235));
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                truoc();
            }
        });

        pnlMove.add(btnPre);

        ImageIcon next = new ImageIcon(getClass().getResource("/icon/Next1.png"));
        btnNext = new JButton(next);
        System.out.println(next);
        btnNext.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnNext.setFont(new Font("Source Sans Pro", 3, 20));
//                btnNext.setBackground(new Color(255, 240, 235));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sau();
            }
        });
        pnlMove.add(btnNext);

        ImageIcon last = new ImageIcon(getClass().getResource("/icon/last.png"));
        btnLast = new JButton(last);
        btnLast.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnLast.setFont(new Font("Source Sans Pro", 3, 20));
//                btnLast.setBackground(new Color(255, 240, 235));
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cuoi();
            }
        });
        pnlMove.add(btnLast);
        pnlTextfield.add(pnlMove);
        ImageIcon back = new ImageIcon(getClass().getResource("/icon/Back.png"));
        btnQuayLai = new JButton("Quay Lại",back);
        btnQuayLai.setFont(new Font("Source Sans Pro", 3, 20));
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quayLai();
            }
        });
        pnlQuayLai.add(btnQuayLai);
        
        DanhSach = new JPanel();
        DanhSach.setFont(new Font("Source Sans Pro", 1, 20));
        

        fillToTable();
        tblDanhSach = new JTable(model);
//        tblDanhSach.setPreferredSize(new Dimension(cw , ch * 9));
        tblDanhSach.setFont(new Font("Source Sans Pro", 1, 15));
        tblDanhSach.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    row = tblDanhSach.getSelectedRow();
                }
                if (row > 0) {
                    Edit();
                }
            }
        });

        JScrollPane sp = new JScrollPane(tblDanhSach);
        sp.setPreferredSize(new Dimension(cw * 10-50, ch * 5));

        btnXuatDS = new JButton("Xuất Danh Sách");
        btnXuatDS.setFont(new Font("Source Sans Pro", 1, 20));
        btnXuatDS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                xuatDSExcel();
            }
        });
        pnlXuatDS.add(btnXuatDS);
        NhanSu.add(pnlChucNang);
        NhanSu.add(pnlQuayLai);
        mainTab.add("Nhan Su", NhanSu);

        DanhSach.add(sp);
        DanhSach.add(pnlXuatDS);
        mainTab.add("Danh Sach", DanhSach);
        
        mainFrame.add(mainTab);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new quanLiNhanSu();
    }

    private void fillToTable() {
        model = new DefaultTableModel(columns, 0);
        try {
            List<NhanVien> listNV = nvdao.SelectAll();

            for (NhanVien nv : listNV) {
                String anMk = "";
                for (int i = 0; i < nv.getMatKhau().length(); i++) {
                    anMk += "*";
                }

                Object[] data = {
                    nv.getEmailNV(), anMk, nv.getTenNV(), nv.getSoDT(), nv.getNgaySinh(), nv.isGioiTinh() ? "Nam" : "Nu",
                    nv.getDiaChi(), nv.isVaiTro() ? "Truong Phong" : "Nhan Vien", nv.getHinh(), nv.isTrangThai() ? "Binh Thuong" : "Da Xoa"
                };
                model.addRow(data);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setForm(NhanVien nv) {
        txtEmail.setText(nv.getEmailNV());
        txtMatKhau.setText(nv.getMatKhau());
        txtHoTen.setText(nv.getTenNV());
        txtngaySinh.setText(String.valueOf(nv.getNgaySinh()));
        txtSoDT.setText(nv.getSoDT());
        rdoNam.setSelected(nv.isGioiTinh());
        rdoNu.setSelected(!nv.isGioiTinh());
        txtDiaChi.setText(nv.getDiaChi());
        rdoTruongPhong.setSelected(nv.isVaiTro());
        rdoNhanVien.setSelected(!nv.isVaiTro());

        if (nv.getHinh() != null) {
            lblHinh.setIcon(XImage.readNhanSu(nv.getHinh()));
            lblHinh.setToolTipText(nv.getHinh());
            lblHinh.setText("");
            
        }
        else
        {
            lblHinh.setIcon(null);
        }

        if (nv.isTrangThai()) {
            txtTrangThai.setText("Binh Thuong");
        } else {
            txtTrangThai.setText("Da Xoa");
        }
    }

    NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setEmailNV(txtEmail.getText());
        nv.setMatKhau(String.valueOf(txtMatKhau.getPassword()));
        nv.setTenNV(txtHoTen.getText());
        nv.setSoDT(txtSoDT.getText());
        nv.setNgaySinh(Date.valueOf(txtngaySinh.getText()));
        nv.setGioiTinh(rdoNam.isSelected());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setVaiTro(rdoTruongPhong.isSelected());
        nv.setHinh(lblHinh.getToolTipText());
        nv.setTrangThai(true);
        return nv;
    }

    private void Edit() {
        try {
            boolean edit = this.row >= 0;
            boolean first = this.row == 0;
            boolean last = this.row == tblDanhSach.getRowCount() - 1;
//            if (!Auth.isManager()) {
//                String maNV = (String) tblDanhSach.getValueAt(this.row, 0);
//                NhanVien model = nvdao.SelectById(maNV);
//                if (model != null) {
//                    setForm(model);
//                    btnThem.setEnabled(false);
//                    btnXoa.setEnabled(false);
//                    btnSua.setEnabled(false);
//                    btnMoi.setEnabled(false);
//                    btnFirst.setEnabled(edit && !first);
//                    btnPre.setEnabled(edit && !first);
//                    btnNext.setEnabled(edit && !last);
//                    btnLast.setEnabled(edit && !last);
//
//                    mainTab.setSelectedIndex(0);
//                }
//            } else {
                String maNV = (String) tblDanhSach.getValueAt(this.row, 0);
                NhanVien model = nvdao.SelectById(maNV);
                if (model != null) {
                    setForm(model);
                    btnMoi.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnFirst.setEnabled(edit && !first);
                    btnPre.setEnabled(edit && !first);
                    btnNext.setEnabled(edit && !last);
                    btnLast.setEnabled(edit && !last);
                    mainTab.setSelectedIndex(0);
//                }
            }

        } catch (Exception e) {
//                    MsgBox.alert(null, "Lỗi truy vấn dữ liệu!!!");
            throw new RuntimeException(e);
        }
    }

    private void chonAnh() {
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//           String dda = "D:\\DuAn1_PRO1014\\CINEMA\\src\\img";
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục logos
            ImageIcon icon = XImage.readNhanSu(file.getName()); // đọc hình từ logos
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }

    private void clearForm() {
        NhanVien nv = new NhanVien();
        this.setForm(nv);
        this.row = -1;
        txtEmail.setText("");
        txtMatKhau.setText("");
        txtHoTen.setText("");
        txtSoDT.setText("");
        txtngaySinh.setText("");
        btngGioiTinh.clearSelection();
        txtDiaChi.setText("");
        btngVaiTro.clearSelection();
        lblHinh.setText("Hinh");
        lblHinh.setToolTipText(null);
        txtTrangThai.setText("Binh Thuong");
    }

    private void insert() {
        NhanVien nv = this.getForm();
        try {
            nvdao.Insert(nv);
            this.fillToTable();
            this.clearForm();
            MsgBox.alert(null, "Nhân Viên vừa được thêm mới!");
        } catch (Exception e) {
            MsgBox.alert(null, "Nhân Viên Không được thêm vào danh sách!");
            throw new RuntimeException(e);
        }
    }

    private void delete() {
        String email = txtEmail.getText();
        try {
            nvdao.Delete(email);
            this.fillToTable();
            this.clearForm();

            MsgBox.alert(null, "Xóa Thành Công Nhân Viên Ra Khỏi DS!");
        } catch (Exception e) {
            MsgBox.alert(null, "Không Xóa Được Nhân Viên Này !");
        }
    }

    private void update() {
        NhanVien nv = this.getForm();
        try {
            nvdao.Update(nv);
            this.fillToTable();
            this.clearForm();
            MsgBox.alert(null, "Nhân Viên vừa được cập nhật mới!");
        } catch (Exception e) {
            MsgBox.alert(null, "Nhân Viên Không được cập nhập chưa được vào danh sách!");
            throw new RuntimeException(e);
        }
    }

    private void dau() {
        this.row = 0;
        this.Edit();
    }

    private void sau() {
        if (this.row < tblDanhSach.getRowCount() - 1) {
            this.row++;
            this.Edit();
        }
    }

    private void cuoi() {
        this.row = tblDanhSach.getRowCount() - 1;
        this.Edit();
    }

    private void truoc() {
        if (this.row > 0) {
            this.row--;
            this.Edit();
        }
    }

    private boolean validate() {
        String email = "\\w+@\\w+(\\.\\w+){1,2}";
        Pattern pattern = Pattern.compile(email);
        Matcher matcher = pattern.matcher(txtEmail.getText());
        if (!matcher.matches()) {
            MsgBox.alert(null, "Email Không Hợp Lệ!");
            txtEmail.requestFocus();
            return false;
        }

        if (txtMatKhau.getPassword().length < 2 || txtMatKhau.getPassword().equals("")) {
            MsgBox.alert(null, "Pass ngắn hơn 2 kí tự hoặc rỗng");
            txtMatKhau.requestFocus();
            return false;
        }

        if (txtHoTen.getText().equals("")) {
            MsgBox.alert(null, "Tên Không Được Rỗng rỗng");
            txtHoTen.requestFocus();
            return false;
        }

        if (txtSoDT.getText().equals("")) {
            MsgBox.alert(null, "Số ĐT Không Được Rỗng rỗng");
            txtSoDT.requestFocus();
            return false;
        }

        if (txtngaySinh.getText().equals("")) {
            MsgBox.alert(null, "Ngày Sinh Không Được Rỗng ");
            txtngaySinh.requestFocus();
            return false;
        }

        if (!rdoNam.isSelected() || !rdoNu.isSelected()) {
            MsgBox.alert(null, " Giới Tính Chưa Được Chọn. Nếu Khống Chọn Lại Mặc Định: Nữ");
            rdoNu.setSelected(true);
            return false;
        }

        if (txtDiaChi.getText().equals("")) {
            MsgBox.alert(null, "Địa Chỉ Không Được Rỗng");
            txtDiaChi.requestFocus();
            return false;
        }

        if (!rdoTruongPhong.isSelected() || !rdoNhanVien.isSelected()) {
            MsgBox.alert(null, "Chức Vụ Chưa Được Chọn. Nếu Khống Chọn Lại Mặc Định: Nhân Viên");
            rdoNhanVien.setSelected(true);
            return false;
        }

        if (lblHinh.getText().equals("")) {
            MsgBox.alert(null, "Vui Lòng Chọn Hình!");
            return false;
        }
        return true;
    }

    private void quayLai() {
//        new DangNhapNV();
        mainFrame.dispose();
    }
//
//    private void xuatDSExcel() {
//        {
//            List<NhanVien> listNV = nvdao.SelectAll();
//            try {
//                XSSFWorkbook wb = new XSSFWorkbook(); // New file
//                XSSFSheet sheet = wb.createSheet("Nhân Sự"); // Tạo sheet có tên là Danh Sách NV
//                XSSFRow row = null; // tạo hàng
//                XSSFCell cell = null; // tạo cột
//                row = sheet.createRow(1); // Tạo 3 dòng trống 
////            cell = row.createCell(0,CellType.STRING); // set type
////            cell.setCellValue("STT"); // set tên cột
//
//                cell = row.createCell(0, CellType.STRING); // set type
//                cell.setCellValue("Email NV"); // set tên cột
//
//                cell = row.createCell(1, CellType.STRING); // set type
//                cell.setCellValue("Mật Khẩu NV"); // set tên cột
//
//                cell = row.createCell(2, CellType.STRING); // set type
//                cell.setCellValue("Tên NV"); // set tên cột
//
//                cell = row.createCell(3, CellType.STRING); // set type
//                cell.setCellValue("Số DT"); // set tên cột
//
//                cell = row.createCell(4, CellType.STRING); // set type
//                cell.setCellValue("Ngày Sinh"); // set tên cột
//
//                cell = row.createCell(5, CellType.STRING); // set type
//                cell.setCellValue("Giới Tính"); // set tên cột
//
//                cell = row.createCell(6, CellType.STRING); // set type
//                cell.setCellValue("Địa Chỉ"); // set tên cột
//
//                cell = row.createCell(7, CellType.STRING); // set type
//                cell.setCellValue("Vai Trò"); // set tên cột
//
//                cell = row.createCell(8, CellType.STRING); // set type
//                cell.setCellValue("Hình"); // set tên cột
//
//                cell = row.createCell(9, CellType.STRING); // set type
//                cell.setCellValue("Trạng Thái"); // set tên cột
//
//                for (int i = 0; i < listNV.size(); i++) {
//
//                    NhanVien nv = listNV.get(i);
//                    String anMk = "";
//                    for (int a = 0; a < nv.getMatKhau().length(); a++) {
//                        anMk += "*";
//                    }
//                    row = sheet.createRow(2 + i);
//
////                cell = row.createCell(0, CellType.NUMERIC);
////                cell.setCellValue(i+1);
//                    cell = row.createCell(0, CellType.STRING);
//                    cell.setCellValue(nv.getEmailNV());
//
//                    cell = row.createCell(1, CellType.STRING);
//                    cell.setCellValue(anMk);
//
//                    cell = row.createCell(2, CellType.STRING);
//                    cell.setCellValue(nv.getTenNV());
//
//                    cell = row.createCell(3, CellType.STRING);
//                    cell.setCellValue(nv.getSoDT());
//
//                    cell = row.createCell(4, CellType.STRING);
//                    cell.setCellValue(String.valueOf(nv.getNgaySinh()));
//
//                    cell = row.createCell(5, CellType.STRING);
//                    cell.setCellValue(nv.isGioiTinh() ? "Nam" : "Nữ");
//
//                    cell = row.createCell(6, CellType.STRING);
//                    cell.setCellValue(nv.getDiaChi());
//
//                    cell = row.createCell(7, CellType.STRING);
//                    cell.setCellValue(nv.isVaiTro() ? "Trưởng Phòng" : "Nhân Viên");
//
//                    cell = row.createCell(8, CellType.STRING);
//                    cell.setCellValue(nv.getHinh());
//
//                    cell = row.createCell(9, CellType.STRING);
//                    cell.setCellValue(nv.isTrangThai() ? "Bình Thường" : "Đã Xóa");
//                }
//
//                try {
//                    //Tạo file để gọi
//                    File f = new File("D:\\Duan1_Pro1014\\CINEMA-20231127T032041Z-001\\CINEMA\\DanhSachNhanSu.xlsx");
//                    FileOutputStream fis = new FileOutputStream(f);
//                    wb.write(fis);
//                    fis.close();
//                } catch (Exception e) {
//                    MsgBox.alert(null, "Lỗi xuất file !");
//                    throw new RuntimeException(e);
//                }
//
//                MsgBox.alert(null, "Xuất file thành công !!");
//            } catch (Exception e) {
//                MsgBox.alert(null, "Lối xuất file Excel !!!");
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
