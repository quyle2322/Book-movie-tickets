/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyNhanSu;

import DAO.PhimDAO;
import entity.Phim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
public class quanLiPhim {

    int w = 1100, h = 950;
    int cw = w / 10, ch = h / 10;
    int x = 10, y = 10;

    DefaultTableModel model;
    PhimDAO pdao = new PhimDAO();
    Object[] columns = {"Mã Phim", "Tên Phim", "Thời Lượng", "Thể Loại",
        "Năm SX", "Nước SX", "Nội Dung", "Poster", "Diễn Viên", "Trang Thai"};
    int row = -1;
    JFileChooser fileChooser = new JFileChooser();
    JFrame mainFrame;
    JTabbedPane mainTab;
    JPanel Phim, DanhSach, pnlTT, pnlChucNang, pnlLable, pnlTextfield, pnlTheLoai, pnlDienVien, pnlQuayLai, pnlXuatDS, pnlPoster, pnlHinhDienVien;
    JTable tblDanhSach;
    JLabel lblMaPhim, lblTenPhim, lblThoiLuong, lblTheLoai, lblNamSX, lblNuocSX, lblNoiDung, lblPoster, lblDienVien, lblChonHinh;
    JTextField txtmaPhim, txttenPhim, txtthoiLuong, txttheLoai, txtnamSX, txtnuocSX, txtTrangThai, txtDienVien;
    JTextArea txtnoiDung;
    JRadioButton rdoDrama, rdoTinhCam, rdoHanhDong, rdoLichSu, rdoHaiHanhDong, rdoHaiKich, rdoSieuNhien, rdoTieuThuyet, rdoKhac;
    ButtonGroup btngTheLoai;
    JButton btnThem, btnXoa, btnSua, btnMoi, btnQuayLai, btnFirst, btnPre, btnNext, btnLast, btnXuatDS, btnThemHinhDV;

    public quanLiPhim() {
        myGui();
    }

    @SuppressWarnings("empty-statement")
    private void myGui() {
        mainFrame = new JFrame("Quan Li Phim");
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
        Phim = new JPanel();
        Phim.setLayout(new FlowLayout());
        Phim.setBounds(x, y, cw * 10, ch * 10);

        pnlLable = new JPanel(new GridLayout(8, 1, 10, 10));
        pnlLable.setPreferredSize(new Dimension(cw - 10, ch * 5 - 50));
        pnlLable.setBorder(new LineBorder(Color.black, 1, true));

        pnlTextfield = new JPanel(new GridLayout(8, 1, 10, 10));
        pnlTextfield.setPreferredSize(new Dimension(cw * 6, ch * 5 - 50));
        pnlTextfield.setBorder(new LineBorder(Color.black, 1, true));

        pnlPoster = new JPanel(new GridLayout(1, 1));
        pnlPoster.setPreferredSize(new Dimension(cw * 3 - 70, ch * 5 - 50));
        pnlPoster.setBorder(new LineBorder(Color.black, 1, true));

        pnlTheLoai = new JPanel(new GridLayout(2, 1));
        pnlTheLoai.setPreferredSize(new Dimension(cw * 9, ch));

        pnlDienVien = new JPanel();
        pnlDienVien.setPreferredSize(new Dimension(cw * 9 + 40, ch - 40));
//        pnlDienVien.setBorder(new LineBorder(Color.black, 1, true));

        pnlHinhDienVien = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlHinhDienVien.setPreferredSize(new Dimension(cw * 9 + 30, ch * 2 - 25));
        pnlHinhDienVien.setBorder(new LineBorder(Color.black, 1, true));

        pnlQuayLai = new JPanel(new GridLayout(1, 1));
        pnlQuayLai.setPreferredSize(new Dimension(cw * 3, ch - 50));
        pnlQuayLai.setBorder(new LineBorder(Color.black, 2, true));

        pnlXuatDS = new JPanel(new GridLayout(1, 1));
        pnlXuatDS.setPreferredSize(new Dimension(cw * 5, ch - 30));
        pnlXuatDS.setBorder(new LineBorder(Color.black, 2, true));
        pnlTT = new JPanel();

        pnlTT.setPreferredSize(new Dimension(cw * 10 - 60, ch * 7));
        pnlTT.setBorder(new LineBorder(Color.black, 1, true));
//        pnlTT.setBackground(new Color(255, 240, 235));
        pnlTT.add(pnlPoster);
        pnlTT.add(pnlLable);
        pnlTT.add(pnlTextfield);

        y += 10;
        lblMaPhim = new JLabel("Mã Phim: ");
//        lblMaPhim.setPreferredSize(new Dimension(cw, ch));
//        lblemailNV.setBounds(x, y, cw, ch);
        lblMaPhim.setFont(new Font("Source Sans Pro", 3, 20));
        lblMaPhim.setOpaque(true);
//        lblemailNV.setBackground(new Color(255, 240, 235));
//        lblemailNV.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblMaPhim);

        txtmaPhim = new JTextField();
//        txtmaPhim.setPreferredSize(new Dimension(cw , ch ));
        txtmaPhim.setFont(new Font("Source Sans Pro", 3, 20));

        pnlTextfield.add(txtmaPhim);

        y += 10;
        lblTenPhim = new JLabel("Tên Phim: ");
        lblTenPhim.setPreferredSize(new Dimension(cw / 2 + 50, ch / 2));
//        lblmatKhau.setBounds(x, y, cw+100, ch );
        lblTenPhim.setFont(new Font("Source Sans Pro", 3, 20));
        lblTenPhim.setOpaque(true);
//        lblmatKhau.setBackground(new Color(255, 240, 235));
//        lblmatKhau.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblTenPhim);

        txttenPhim = new JTextField();
        txttenPhim.setPreferredSize(new Dimension(cw * 7 + 50, ch - 50));
        txttenPhim.setFont(new Font("Source Sans Pro", 3, 20));

        pnlTextfield.add(txttenPhim);

        y += 10;
        lblThoiLuong = new JLabel("Thời Lượng: ");
//        lblhoTen.setPreferredSize(new Dimension(cw , ch));
        lblThoiLuong.setBounds(x, y, cw + 100, ch);
        lblThoiLuong.setFont(new Font("Source Sans Pro", 3, 20));
        lblThoiLuong.setOpaque(true);
//        lblhoTen.setBackground(new Color(255, 240, 235));
//        lblhoTen.setBorder(new LineBorder(Color.black, 1, true));
        pnlLable.add(lblThoiLuong);

        txtthoiLuong = new JTextField();
        txtthoiLuong.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtthoiLuong.setFont(new Font("Source Sans Pro", 3, 20));

        pnlTextfield.add(txtthoiLuong);

        lblTheLoai = new JLabel("Thể Loại: ");
//        lblGioiTinh.setBounds(x+10, y+10, cw+25, ch-30);
        lblTheLoai.setPreferredSize(new Dimension(cw, ch));
        lblTheLoai.setFont(new Font("Source Sans Pro", 3, 20));
        lblTheLoai.setOpaque(true);
//        lblGioiTinh.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblTheLoai);

        rdoHaiKich = new JRadioButton("Hài Kịch");
        rdoHaiKich.setPreferredSize(new Dimension(cw, ch));
        rdoHaiKich.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNam.setBackground(new Color(255, 240, 235));
        rdoHaiKich.setSelected(true);
        pnlTheLoai.add(rdoHaiKich);

        rdoHaiHanhDong = new JRadioButton("Hài Hành Động");
        rdoHaiHanhDong.setPreferredSize(new Dimension(cw, ch));
        rdoHaiHanhDong.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNam.setBackground(new Color(255, 240, 235));
//        rdoHaiHanhDong.setSelected(true);
        pnlTheLoai.add(rdoHaiHanhDong);

        rdoDrama = new JRadioButton("Drama");
        rdoDrama.setPreferredSize(new Dimension(cw, ch));
        rdoDrama.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlTheLoai.add(rdoDrama);

        rdoTinhCam = new JRadioButton("Tình cảm");
        rdoTinhCam.setPreferredSize(new Dimension(cw, ch));
        rdoTinhCam.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlTheLoai.add(rdoTinhCam);

        rdoHanhDong = new JRadioButton("Hành Động");
        rdoHanhDong.setPreferredSize(new Dimension(cw, ch));
        rdoHanhDong.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlTheLoai.add(rdoHanhDong);

        rdoLichSu = new JRadioButton("Lịch Sử");
        rdoLichSu.setPreferredSize(new Dimension(cw, ch));
        rdoLichSu.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlTheLoai.add(rdoLichSu);

        rdoSieuNhien = new JRadioButton("Siêu Nhiên");
        rdoSieuNhien.setPreferredSize(new Dimension(cw, ch));
        rdoSieuNhien.setFont(new Font("Source Sans Pro", 3, 20));
//        rdoNu.setBackground(new Color(255, 240, 235));
        pnlTheLoai.add(rdoSieuNhien);

        rdoTieuThuyet = new JRadioButton("Tiểu Thuyết");
        rdoTieuThuyet.setPreferredSize(new Dimension(cw, ch));
        rdoTieuThuyet.setFont(new Font("Source Sans Pro", 3, 20));
        pnlTheLoai.add(rdoTieuThuyet);

        rdoKhac = new JRadioButton("Khác");
        rdoKhac.setPreferredSize(new Dimension(cw, ch));
        rdoKhac.setFont(new Font("Source Sans Pro", 3, 20));
        pnlTheLoai.add(rdoKhac);

        pnlTextfield.add(pnlTheLoai);
        btngTheLoai = new ButtonGroup();
        btngTheLoai.add(rdoHaiHanhDong);
        btngTheLoai.add(rdoDrama);
        btngTheLoai.add(rdoHanhDong);
        btngTheLoai.add(rdoLichSu);
        btngTheLoai.add(rdoSieuNhien);
        btngTheLoai.add(rdoTinhCam);
        btngTheLoai.add(rdoHaiKich);
        btngTheLoai.add(rdoKhac);
        btngTheLoai.add(rdoTieuThuyet);

        lblNamSX = new JLabel("Năm SX: ");
//        lblNamSX.setPreferredSize(new Dimension(cw, ch));
        lblNamSX.setFont(new Font("Source Sans Pro", 3, 20));
        lblNamSX.setOpaque(true);
//        lblNamSX.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblNamSX);
//
        txtnamSX = new JTextField();
//        txtnamSX.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtnamSX.setFont(new Font("Source Sans Pro", 3, 20));
        pnlTextfield.add(txtnamSX);

        lblNuocSX = new JLabel("Nước SX: ");
//        lblGioiTinh.setBounds(x+10, y+10, cw+25, ch-30);
        lblNuocSX.setPreferredSize(new Dimension(cw, ch));
        lblNuocSX.setFont(new Font("Source Sans Pro", 3, 20));
        lblNuocSX.setOpaque(true);
//        lblChucVu.setBackground(new Color(255, 240, 235));
        pnlLable.add(lblNuocSX);

        txtnuocSX = new JTextField();
        txtnuocSX.setFont(new Font("Source Sans Pro", 3, 20));
        pnlTextfield.add(txtnuocSX);

        lblNoiDung = new JLabel("Nội Dung: ");
        lblNoiDung.setPreferredSize(new Dimension(cw, ch));
        lblNoiDung.setFont(new Font("Source Sans Pro", 3, 20));
        lblNoiDung.setOpaque(true);
        pnlLable.add(lblNoiDung);

        txtnoiDung = new JTextArea();
        txtnoiDung.setFont(new Font("Source Sans Pro", 3, 20));
//        JScrollPane spNoiDung = new JScrollPane(txtnoiDung);
//        spNoiDung.setPreferredSize(new Dimension(cw*2, ch));
        pnlTextfield.add(txtnoiDung);

        lblPoster = new JLabel("Poster", JLabel.CENTER);
        lblPoster.setPreferredSize(new Dimension(cw * 3 - 70, ch * 5 - 50));
        lblPoster.setFont(new Font("Source Sans Pro", 3, 20));
        lblPoster.setBorder(new LineBorder(Color.black, 1, true));
        lblPoster.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                lblPoster.setText("");
                chonAnhPoster();
            }
        });
        pnlPoster.add(lblPoster);

        lblDienVien = new JLabel("Tên DV: ");
        lblDienVien.setPreferredSize(new Dimension(cw, ch - 50));
        lblDienVien.setFont(new Font("Source Sans Pro", 3, 20));
        lblDienVien.setBorder(new LineBorder(Color.black, 1, true));
        pnlDienVien.add(lblDienVien);

        txtDienVien = new JTextField();
        txtDienVien.setPreferredSize(new Dimension(cw * 6 + 25, ch - 50));
        txtDienVien.setFont(new Font("Source Sans Pro", 3, 20));
//        txtDienVien.setBorder(new LineBorder(Color.black, 2, true));
        pnlDienVien.add(txtDienVien);

        btnThemHinhDV = new JButton("Thêm Hình DV");
        btnThemHinhDV.setPreferredSize(new Dimension(cw * 2, ch - 50));
        btnThemHinhDV.setFont(new Font("Source Sans Pro", 3, 20));
        btnThemHinhDV.setBorder(new LineBorder(Color.black, 2, true));
        btnThemHinhDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    lblChonHinh = new JLabel("Chọn Hình", JLabel.CENTER);
                    lblChonHinh.setPreferredSize(new Dimension(cw + 90, ch * 2 - 40));
                    lblChonHinh.setFont(new Font("Source Sans Pro", 3, 20));
                    lblChonHinh.setBorder(new LineBorder(Color.black, 2, true));
                    btnThemHinhDV.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent me) {
                            lblChonHinh.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent me) {
                                    lblChonHinh.setText("");
                                    chonAnhDienVien2();
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                pnlHinhDienVien.add(lblChonHinh);
                mainFrame.setSize(w - 1, h);
                mainFrame.setSize(w, h);
            }
        });
        pnlDienVien.add(btnThemHinhDV);
        pnlTT.add(pnlHinhDienVien);
        pnlTT.add(pnlDienVien);

        txtTrangThai = new JTextField("Trang Thai");
        txtTrangThai.setPreferredSize(new Dimension(cw * 7, ch - 50));
        txtTrangThai.setFont(new Font("Source Sans Pro", 3, 20));
        txtTrangThai.setEnabled(false);
        pnlTextfield.add(txtTrangThai);

//
        Phim.add(pnlTT);
//
        pnlChucNang = new JPanel();
        pnlChucNang.setPreferredSize(new Dimension(cw * 7, ch + 20));
//        pnlChucNang.setBackground(new Color(255, 240, 235));
        pnlChucNang.setBorder(new LineBorder(Color.black, 1, true));
        pnlChucNang.setLayout(new GridLayout(2, 2, 5, 5));
//
        // Button Them
        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/icon/Add.png"));
        btnThem = new JButton("Add", iconAdd);
        btnThem.setPreferredSize(new Dimension(cw + 100, ch - 50));
        btnThem.setFont(new Font("Source Sans Pro", 3, 20));
//        btnThem.setBackground(new Color(255, 240, 235));
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Phim p = new Phim();
                try {
                    if (txtmaPhim.getText().equals(p.getMaPhim())) {
                        MsgBox.alert(null, "Mã Phim đã tồn tại");
                    } else {
                        insert();
                    }
                } catch (Exception e) {

                }

            }
        });
        pnlChucNang.add(btnThem);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/icon/Delete.png"));
        btnXoa = new JButton("Delete", iconDelete);
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
        btnSua = new JButton("Update", iconUpdate);
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
        btnMoi = new JButton("Clear", iconClear);
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
        pnlChucNang.add(btnFirst);

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

        pnlChucNang.add(btnPre);

        ImageIcon next = new ImageIcon(getClass().getResource("/icon/Next1.png"));
        btnNext = new JButton(next);
        btnNext.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnNext.setFont(new Font("Source Sans Pro", 3, 20));
//                btnNext.setBackground(new Color(255, 240, 235));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sau();
            }
        });
        pnlChucNang.add(btnNext);

        ImageIcon last = new ImageIcon(getClass().getResource("/icon/last.png"));
        btnLast = new JButton(last);
        btnLast.setPreferredSize(new Dimension(cw + 60, ch - 50));
        btnLast.setFont(new Font("Source Sans Pro", 3, 20));
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cuoi();
            }
        });
        pnlChucNang.add(btnLast);

        ImageIcon back = new ImageIcon(getClass().getResource("/icon/back.png"));
        btnQuayLai = new JButton(back);

        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quayLai();
            }
        });
        pnlQuayLai.add(btnQuayLai);
//
//     
        DanhSach = new JPanel();
        DanhSach.setFont(new Font("Source Sans Pro", 1, 20));
//
        fillToTable();
        tblDanhSach = new JTable(model);
        tblDanhSach.setPreferredSize(new Dimension(cw * 10, ch * 9));
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
//
        JScrollPane sp = new JScrollPane(tblDanhSach);
        sp.setPreferredSize(new Dimension(cw * 9, ch * 5));

        btnXuatDS = new JButton("Xuất Danh Sách");
        btnXuatDS.setFont(new Font("Source Sans Pro", 1, 20));
        btnXuatDS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                xuatDSExcel();
            }
        });
        pnlXuatDS.add(btnXuatDS);
        Phim.add(pnlChucNang);
//
        DanhSach.add(sp);
        DanhSach.add(pnlXuatDS);
        mainTab.add("Phim", Phim);
        mainTab.add("Danh Sach", DanhSach);
        Phim.add(pnlQuayLai);
        mainFrame.add(mainTab);
        mainFrame.setVisible(true);
    }

//
    private void fillToTable() {
        model = new DefaultTableModel(columns, 0);
        try {
            List<Phim> listPhim = pdao.SelectAll();

            for (Phim p : listPhim) {

                Object[] data = {
                    p.getMaPhim(), p.getTenPhim(), p.getThoiLuong(), p.getTheLoai(), p.getNamSX(), p.getNuocSX(),
                    p.getNoiDung(), p.getPoster(), p.getDienVien(), p.isTrangThai() ? "Binh Thuong" : "Da Xoa"
                };
                model.addRow(data);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//

    private void setForm(Phim p) {
        String [] dienVien = {};
        System.out.println(p.getDienVien());
        if(p.getDienVien() != null)
            dienVien = p.getDienVien().split(",");
        
        try {
            txtmaPhim.setText(p.getMaPhim());
            txttenPhim.setText(p.getTenPhim());
            txtthoiLuong.setText(String.valueOf(p.getThoiLuong()));
            if (p.getTheLoai() == null) {
                btngTheLoai.clearSelection();
            } else {
                if (p.getTheLoai().equalsIgnoreCase("Hài")) {
                    rdoHaiKich.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Drama")) {
                    rdoDrama.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Tình Cảm")) {
                    rdoTinhCam.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Hài Hành Động")) {
                    rdoHaiHanhDong.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Lịch Sử")) {
                    rdoLichSu.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Siêu Nhiên")) {
                    rdoSieuNhien.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Hành động")) {
                    rdoHanhDong.setSelected(true);
                } else if (p.getTheLoai().equalsIgnoreCase("Tiểu thuyết")) {
                    rdoTieuThuyet.setSelected(true);
                } else {
                    rdoKhac.setSelected(true);
                }
            }

            txtnamSX.setText(String.valueOf(p.getNamSX()));
            txtnuocSX.setText(p.getNuocSX());
            txtnoiDung.setText(p.getNoiDung());

            if (p.getPoster() != null) {
                lblPoster.setIcon(XImage.readPoster(p.getPoster()));
                lblPoster.setToolTipText(p.getPoster());
                lblPoster.setText("");
                lblPoster.setBorder(null);
            } else {
                lblPoster.setIcon(null);
                lblPoster.setBorder(new LineBorder(Color.black, 1, true));
            }

            txtDienVien.setText(p.getDienVien());

            if (p.isTrangThai()) {
                txtTrangThai.setText("Binh Thuong");
            } else {
                txtTrangThai.setText("Da Xoa");
            }

            pnlHinhDienVien.removeAll();
           
                for (int i = 0; i < dienVien.length; i++) {
                    JLabel hinhDV = new JLabel();
                    hinhDV.setPreferredSize(new Dimension(cw + 90, ch * 2));
//                hinhDV.setBorder(new LineBorder(Color.black, 2, true));
                    hinhDV.setIcon(XImage.readTenDienVien(dienVien[i].trim(), cw, ch));
//                pnlHinhDienVien.setBorder(new LineBorder(Color.yellow, 2, true));
                    pnlHinhDienVien.add(hinhDV);
                }
            mainFrame.setSize(w-1,h);
            mainFrame.setSize(w,h);
        } catch (Exception e) {
            MsgBox.alert(null, "1");
            throw new RuntimeException(e);
        }

    }
//

    Phim getForm() {
        Phim p = new Phim();

        try {

            p.setMaPhim(txtmaPhim.getText());
            p.setTenPhim(txttenPhim.getText());
            p.setThoiLuong(Float.parseFloat(txtthoiLuong.getText()));

            if (rdoHaiKich.isSelected()) {
                p.setTheLoai("Hài");
            } else if (rdoHaiHanhDong.isSelected()) {
                p.setTheLoai("Hài Hành Động");
            } else if (rdoDrama.isSelected()) {
                p.setTheLoai("Drama");
            } else if (rdoTinhCam.isSelected()) {
                p.setTheLoai("Tình Cảm");
            } else if (rdoLichSu.isSelected()) {
                p.setTheLoai("Lịch Sử");
            } else if (rdoSieuNhien.isSelected()) {
                p.setTheLoai("Siêu Nhiên");
            } else if (rdoHanhDong.isSelected()) {
                p.setTheLoai("Hành Động");
            } else if (rdoTieuThuyet.isSelected()) {
                p.setTheLoai("Tiểu Thuyết");
            } else {
                p.setTheLoai("Khác");
            }

            p.setNamSX(Integer.parseInt(txtnamSX.getText()));
            p.setNuocSX(txtnuocSX.getText());
            p.setNoiDung(txtnoiDung.getText());
            p.setPoster(lblPoster.getToolTipText());
            p.setDienVien(txtDienVien.getText());
            p.setTrangThai(true);

        } catch (Exception e) {
            MsgBox.alert(null, "Thông Tin Chưa Đủ!");
            throw new RuntimeException(e);
        }

        return p;
    }
//
//

    private void Edit() {
        try {
            boolean edit = this.row >= 0;
            boolean first = this.row == 0;
            boolean last = this.row == tblDanhSach.getRowCount() - 1;

            String maNV = (String) tblDanhSach.getValueAt(this.row, 0);
            Phim model = pdao.SelectById(maNV);
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
            }
//            }

        } catch (Exception e) {
            MsgBox.alert(null, "Lỗi truy vấn dữ liệu!!!");
            throw new RuntimeException(e);
        }
    }
//

    private void chonAnhPoster() {
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//           String dda = "D:\\DuAn1_PRO1014\\CINEMA\\src\\img";
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục logos
            ImageIcon icon = XImage.readPoster(file.getName()); // đọc hình từ logos
            lblPoster.setIcon(icon);
            lblPoster.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }

    private void chonAnhDienVien2() {
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//           String dda = "D:\\DuAn1_PRO1014\\CINEMA\\src\\img";
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục logos
            ImageIcon icon = XImage.readHinhDienVien(file.getName()); // đọc hình từ logos
            lblChonHinh.setIcon(icon);
            lblChonHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }
//

    private void clearForm() {
        Phim nv = new Phim();

        try {
            this.setForm(nv);
            this.row = -1;
        } catch (Exception e) {
            MsgBox.alert(null, "Sai Clear form");
            throw new RuntimeException(e);
        }

    }
//

    private void insert() {
        Phim p = this.getForm();
        try {
            pdao.Insert(p);
            this.fillToTable();
            this.clearForm();
            MsgBox.alert(null, "Phim vừa được thêm mới!");
        } catch (Exception e) {
            MsgBox.alert(null, "Phim Không được thêm vào danh sách!");
            throw new RuntimeException(e);
        }
    }
//

    private void delete() {
        String email = txtmaPhim.getText();
        try {
            pdao.Delete(email);
            this.fillToTable();
            this.clearForm();

            MsgBox.alert(null, "Xóa Thành Công Nhân Viên Ra Khỏi DS!");
        } catch (Exception e) {
            MsgBox.alert(null, "Không Xóa Được Nhân Viên Này !");
        }
    }
//

    private void update() {
        Phim p = this.getForm();
        try {
            pdao.Update(p);
            this.fillToTable();
            this.clearForm();
            MsgBox.alert(null, "Nhân Viên vừa được cập nhật mới!");
        } catch (Exception e) {
            MsgBox.alert(null, "Nhân Viên Không được cập nhập chưa được vào danh sách!");
            throw new RuntimeException(e);
        }
    }
//

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
//    

    private boolean validate() {

        if (txtmaPhim.getText().equals("")) {
            MsgBox.alert(null, "Ma Phong Không được trống !");
            txtmaPhim.requestFocus();
            return false;
        }

        if (txttenPhim.getText().equals("")) {
            MsgBox.alert(null, "Tên Phim Không Được Trống");
            txttenPhim.requestFocus();
            return false;
        }

        if (txtthoiLuong.getText().equals("")) {
            MsgBox.alert(null, "Thời Lượng Không Được Rỗng rỗng");
            txtthoiLuong.requestFocus();
            return false;
        }

        if (!rdoDrama.isSelected() || !rdoHaiHanhDong.isSelected() || !rdoHanhDong.isSelected() || !rdoLichSu.isSelected()
                || !rdoSieuNhien.isSelected() || !rdoTieuThuyet.isSelected() || !rdoTinhCam.isSelected()) {
            MsgBox.alert(null, "Bạn chưa chọn thể loại cho phim !");

        }

        if (txtnamSX.getText().equals("")) {
            MsgBox.alert(null, "Năm SX Không Được Rỗng ");
            txtnamSX.requestFocus();
            return false;
        }

        if (txtnuocSX.getText().equals("")) {
            MsgBox.alert(null, "Nước SX Không Được Rỗng");
            txtnuocSX.requestFocus();
            return false;
        }

        if (txtnoiDung.getText().equals("")) {
            MsgBox.alert(null, "Nội Dung Phim Không Được Rỗng");
            txtnoiDung.requestFocus();
            return false;
        }

        if (lblPoster.getText().equals("")) {
            MsgBox.alert(null, "Vui Lòng Chọn Ảnh cho Poster!");
            return false;
        }

//        if (lblDienVien1.getText().equals("")) {
//            MsgBox.alert(null, "Vui Lòng chọn Ảnh cho Diễn Viên");
//            return false;
//        }
        return true;
    }

    private void quayLai() {
        new GiaoDienNhanVien.QuanLyChung_2().setVisible(true);
        mainFrame.dispose();
    }
//
//    private void xuatDSExcel() {
//        {
//            List<Phim> listPhim = pdao.SelectAll();
//            try {
//                XSSFWorkbook wb = new XSSFWorkbook(); // New file
//                XSSFSheet sheet = wb.createSheet("Phim"); // Tạo sheet có tên là Danh Sách NV
//                XSSFRow row = null; // tạo hàng
//                XSSFCell cell = null; // tạo cột
//                row = sheet.createRow(1); // Tạo 3 dòng trống 
////            cell = row.createCell(0,CellType.STRING); // set type
////            cell.setCellValue("STT"); // set tên cột
//
//                cell = row.createCell(0, CellType.STRING); // set type
//                cell.setCellValue("Mã Phim"); // set tên cột
//
//                cell = row.createCell(1, CellType.STRING); // set type
//                cell.setCellValue("Tên Phim"); // set tên cột
//
//                cell = row.createCell(2, CellType.STRING); // set type
//                cell.setCellValue("Thời Lượng"); // set tên cột
//
//                cell = row.createCell(3, CellType.STRING); // set type
//                cell.setCellValue("Thể Loại"); // set tên cột
//
//                cell = row.createCell(4, CellType.STRING); // set type
//                cell.setCellValue("Năm SX"); // set tên cột
//
//                cell = row.createCell(5, CellType.STRING); // set type
//                cell.setCellValue("Nước SX"); // set tên cột
//
//                cell = row.createCell(6, CellType.STRING); // set type
//                cell.setCellValue("Nội Dung"); // set tên cột
//
//                cell = row.createCell(7, CellType.STRING); // set type
//                cell.setCellValue("Poster"); // set tên cột
//
//                cell = row.createCell(8, CellType.STRING); // set type
//                cell.setCellValue("Diễn Viên"); // set tên cột
//
//                cell = row.createCell(9, CellType.STRING); // set type
//                cell.setCellValue("Trạng Thái"); // set tên cột
//
//                for (int i = 0; i < listPhim.size(); i++) {
//                    Phim p = listPhim.get(i);
//                    row = sheet.createRow(9 + i);
//
////                cell = row.createCell(0, CellType.NUMERIC);
////                cell.setCellValue(i+1);
//                    cell = row.createCell(0, CellType.STRING);
//                    cell.setCellValue(p.getMaPhim());
//
//                    cell = row.createCell(1, CellType.STRING);
//                    cell.setCellValue(p.getTenPhim());
//
//                    cell = row.createCell(2, CellType.STRING);
//                    cell.setCellValue(p.getThoiLuong());
//
//                    cell = row.createCell(3, CellType.STRING);
//                    cell.setCellValue(p.getTheLoai());
//
//                    cell = row.createCell(4, CellType.STRING);
//                    cell.setCellValue(p.getNamSX());
//
//                    cell = row.createCell(5, CellType.STRING);
//                    cell.setCellValue(p.getNuocSX());
//
//                    cell = row.createCell(6, CellType.STRING);
//                    cell.setCellValue(p.getNoiDung());
//
//                    cell = row.createCell(7, CellType.STRING);
//                    cell.setCellValue(p.getPoster());
//
//                    cell = row.createCell(8, CellType.STRING);
//                    cell.setCellValue(p.getDienVien());
//
//                    cell = row.createCell(9, CellType.STRING);
//                    cell.setCellValue(p.isTrangThai() ? "Bình Thường" : "Đã Xóa");
//                }
//
//                try {
//                    //Tạo file để gọi
//                    File f = new File("D:\\Duan1_Pro1014\\CINEMA-20231127T032041Z-001\\CINEMA\\DanhSachPhim.xlsx");
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

    public static void main(String[] args) {
        new quanLiPhim();
    }
}
