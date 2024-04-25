package DatVe;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAO.KhachHangDAO;
import DAO.PhimDAO;
import DAO.VeDAO;
import entity.Phim;
import entity.SuatChieu;
import entity.Ve;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import utils.Auth;
import utils.JDBCHelper;
import utils.MsgBox;

/**
 *
 * @author PC_
 */
public class DatVe {

    VeDAO vedao = new VeDAO();
    KhachHangDAO khdao = new KhachHangDAO();
    PhimDAO phimDAO = new PhimDAO();
    ArrayList<Date> dsNgayChieu = new ArrayList<Date>();
    ArrayList<SuatChieu> dsSuatChieu = new ArrayList<SuatChieu>();

    Phim phim;
    String maPhim;
    int maSC;
    int slGhe = 60;
    int w, h, cw, ch;
    int x = 10, y = 10, px = 20, py = 10;
    HashMap<Integer, Ve> dsGhe = new HashMap<Integer, Ve>();

    Color color = new Color(255, 240, 235);

    JFrame mainFrame;
    JPanel headerPanel, mainPanel, pnlTrai, pnlGiua, pnlPhai, footerPanel, pnlChuThich, pnlThanhTien, pnlSuatChieu;
    JLabel lblManHinh, lblClose, lblGhe, lblThanhTien, lblSoLuong;

    public DatVe(String maPhim) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.w = (int) screenSize.getWidth();
        this.h = (int) screenSize.getHeight();
        this.ch = w / 20;
        this.cw = w / 20;
        
        this.maPhim = maPhim;
        this.phim = phimDAO.SelectById(maPhim);
        this.setNgayChieu();
        this.setSuatChieu();
        
        if (dsSuatChieu.isEmpty())
            
            MsgBox.alert(mainFrame, "Xin Lỗi, phim hiện chưa có suất chiếu");
        else
        {
            maSC = dsSuatChieu.get(0).getMaSuatChieu();
            this.gheGUI();
            this.suatChieuGUI();
            this.tinhTien();
        }
    }

    private void gheGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize(w, h);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);

        y = 10;
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(x, y, w, 100);

        lblManHinh = new JLabel("MÀN HÌNH", JLabel.CENTER);
        lblManHinh.setFont(new Font("Arial", 1, 50));
        lblManHinh.setBounds(100, 0, cw * 14 - 420, 100);
        lblManHinh.setBorder(new BevelBorder(1, Color.RED, Color.RED));
        headerPanel.add(lblManHinh);

        lblClose = new JLabel("X", JLabel.CENTER);
        lblClose.setFont(new Font("Arial", 1, 50));
        lblClose.setBounds(w - 120, 25, 50, 50);
        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            public void mouseEntered(MouseEvent e) {
                lblClose.setForeground(color);
            }

            public void mouseExited(MouseEvent e) {
                lblClose.setForeground(Color.BLACK);
            }
        });
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerPanel.add(lblClose);

        y += 140;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(x, y, cw * 12, ch * 6 + 50);

        pnlTrai = new JPanel();
        pnlTrai.setBounds(px, py, cw * 2, ch * 6);
        pnlTrai.setLayout(new GridLayout(6, 2, 5, 5));

        px += cw * 2 + 60;

        pnlGiua = new JPanel();
        pnlGiua.setBounds(px, py, cw * 6, ch * 6);
        pnlGiua.setLayout(new GridLayout(6, 6, 5, 5));

        px += cw * 6 + 60;

        pnlPhai = new JPanel();
        pnlPhai.setBounds(px, py, cw * 2, ch * 6);
        pnlPhai.setLayout(new GridLayout(6, 2, 5, 5));

        loadGhe(this.maSC);

        y += ch * 6 + 60;

        mainPanel.add(pnlTrai);
        mainPanel.add(pnlGiua);
        mainPanel.add(pnlPhai);

        footerPanel = new JPanel();
        footerPanel.setLayout(null);
        footerPanel.setBounds(x, y, cw * 14 - 20, h - y - 10);

        pnlChuThich = new JPanel();
        pnlChuThich.setLayout(new GridLayout(3, 1));
        pnlChuThich.setBounds(10, 5, 220, h - y - 30);

        String[] chuThich = {" Đã Được Đặt", " Đang Chọn", " Chưa Chọn"};
        for (int i = 0; i < chuThich.length; i++) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(50, 50));
            label.setBorder(new LineBorder(Color.PINK));
            if (i == 0) {
                label.setIcon(new ImageIcon(getClass().getResource("/img/ordered-48.png")));
            } else if (i == 1) {
                label.setOpaque(true);
                label.setBackground(color);
            }
            panel.add(label);
            JLabel label1 = new JLabel(chuThich[i], JLabel.LEFT);
            label1.setFont(new Font("Arial", 1, 18));
            label1.setPreferredSize(new Dimension(150, (h - y - 30) / 3));
            panel.add(label1);
            pnlChuThich.add(panel);
        }

        footerPanel.add(pnlChuThich);

        pnlThanhTien = new JPanel(new GridLayout(3, 1));
        pnlThanhTien.setBounds(500, 5, 500, h - y - 30);

        lblSoLuong = new JLabel();
        lblSoLuong.setFont(new Font("Arial", 1, 18));
        pnlThanhTien.add(lblSoLuong);

        lblGhe = new JLabel();
        lblGhe.setFont(new Font("Arial", 1, 18));
        pnlThanhTien.add(lblGhe);

        lblThanhTien = new JLabel();
        lblThanhTien.setFont(new Font("Arial", 1, 18));
        pnlThanhTien.add(lblThanhTien);

        footerPanel.add(pnlThanhTien);

        mainFrame.add(headerPanel);
        mainFrame.add(mainPanel);
        mainFrame.add(footerPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new DatVe( "PHIM003");
    }

    private void suatChieuGUI() {
        this.x = cw * 12 + 60;
        this.y = 160;
        pnlSuatChieu = new JPanel();
        pnlSuatChieu.setBounds(x, y, w - cw * 13 - 20, ch * 6);

        JPanel thongTinPhim = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thongTinPhim.setSize(w - cw * 12 - 30, ch * 2);

        JLabel poster = new JLabel();
        poster.setPreferredSize(new Dimension(cw + 30, ch * 2));
        poster.setBorder(new LineBorder(Color.BLACK));
        ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/imgPoster/" + phim.getPoster())).getImage().
                getScaledInstance(cw + 30, ch * 2, Image.SCALE_SMOOTH));
        poster.setIcon(img);
        thongTinPhim.add(poster);

        JPanel tenPhim = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tenPhim.setPreferredSize(new Dimension(w - cw * 14 - 50, ch * 2));

        JLabel lblTenPhim = new JLabel(this.phim.getTenPhim(), JLabel.LEFT);
        lblTenPhim.setFont(new Font("Arial", 1, 18));
        lblTenPhim.setPreferredSize(new Dimension(w - cw * 14 - 60, ch));
        tenPhim.add(lblTenPhim);

        JTextArea txtNoiDung = new JTextArea();
        txtNoiDung.setBackground(new Color(238, 238, 238));
        txtNoiDung.setLineWrap(true);
        txtNoiDung.setText(this.phim.getNoiDung());
        txtNoiDung.setPreferredSize(new Dimension(w - cw * 14 - 100, ch - 20));
        txtNoiDung.setEditable(false);
        tenPhim.add(txtNoiDung);

        thongTinPhim.add(tenPhim);

        JPanel thongTinSC = new JPanel();
        thongTinSC.setPreferredSize(new Dimension(w - cw * 13 - 30, ch * 4 - 20));
        int j = 0;
        for (int i = 0; i < dsNgayChieu.size(); i++) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.setPreferredSize(new Dimension(w - cw * 13 - 30, ch));
            JLabel l = new JLabel(String.valueOf(dsNgayChieu.get(i)) + ":");
            l.setPreferredSize(new Dimension(w - cw * 13 - 30, ch - 60));
            p.add(l);
            for (; j < this.dsSuatChieu.size();) {
                SuatChieu sc = dsSuatChieu.get(j);
                if (!dsSuatChieu.get(j).getNgayChieu().equals(dsNgayChieu.get(i))) {
                    break;
                }
                JButton b = new JButton(sc.getGioChieu());
                b.setPreferredSize(new Dimension(cw + 10, ch / 2));
                b.setName(String.valueOf(sc.getMaSuatChieu()));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        maSC = Integer.parseInt(b.getName());
                        loadGhe(maSC);
                        tinhTien();
                    }
                });
                p.add(b);
                j++;
            }
            thongTinSC.add(p);

        }

        pnlSuatChieu.add(thongTinPhim);
        pnlSuatChieu.add(thongTinSC);
        mainFrame.add(pnlSuatChieu);

    }

    private void loadGhe(Object... args) {
        String sql = "select * from ve where masuatchieu = ? order by maghe";
        List<Ve> list = new ArrayList<Ve>();
        try {
            ResultSet rs = JDBCHelper.Query(sql, args);
            while (rs.next()) {
                Ve ve = new Ve();
                ve.setMaGhe(rs.getString("maghe"));
                ve.setMaSuatChieu(rs.getInt("masuatchieu"));
                ve.setMaVe(rs.getInt("mave"));
                ve.setEmailKH(rs.getString("khachhang"));
                ve.setEmailNV(rs.getString("nhanvien"));
                ve.setThanhTien(rs.getDouble("thanhtien"));
                ve.setThanhToan(rs.getBoolean("thanhtoan"));
                ve.setTinhTrang(Auth.KH.getEmailKH());
                list.add(ve);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char hang = 'A';
        int cot = 1;
        int vitriGhe = 0;

        for (int i = 0; i < this.slGhe; i++) {
            if (cot > 10) {
                int a = (int) hang;
                a++;
                hang = (char) (a);
                cot = 1;
            }
            String maGhe = String.valueOf(hang) + String.valueOf(cot);

            Ve ve = new Ve();
            ve.setVitri(i);
            ve.setMaGhe(maGhe);

            double thanhTien;
            if (i < 20) {
                thanhTien = 60000;
            } else {
                thanhTien = 65000;
            }
            ve.setThanhTien(thanhTien);

            if (vitriGhe == list.size()) {
                vitriGhe--;
            }
            if (vitriGhe >= 0) {
                if (ve.getMaGhe().equals(list.get(vitriGhe).getMaGhe())) {
                    ve = list.get(vitriGhe);
                    vitriGhe++;
                } else {
                    ve.setThanhToan(false);
                }
            }
            this.dsGhe.put(i, ve);
            cot++;
        }
        fillToSeat();
    }

    private void fillToSeat() {
        pnlTrai.removeAll();
        pnlGiua.removeAll();
        pnlPhai.removeAll();
        for (int i = 0; i < this.slGhe; i++) {
            Ve ve = this.dsGhe.get(i);
            JLabel l = new JLabel(ve.getMaGhe(), JLabel.CENTER);
            if (ve.isThanhToan() || ve.getTinhTrang() == 2) {
                l.setText("");
                l.setIcon(new ImageIcon(getClass().getResource("/img/ordered.png")));
            } else if (ve.getTinhTrang() == 1) {
                l.setOpaque(true);
                l.setBackground(this.color);
            }

            l.setBorder(new LineBorder(Color.pink));
            l.setCursor(new Cursor(Cursor.HAND_CURSOR));
            l.setName(String.valueOf(i));
            l.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (!l.getText().equals("")) {
                        Ve ve = dsGhe.get(Integer.parseInt(l.getName()));
                        ve.setMaVe(16);
                        ve.setMaSuatChieu(maSC);
                        ve.setEmailKH(Auth.KH.getEmailKH());
                        ve.setThanhToan(false);
                        datVe(ve);
                    } else {
                        MsgBox.alert(null, "Vé đã được đặt");
                    }
                }
            });
            int cot = Integer.parseInt(ve.getMaGhe().substring(1));

            if (cot <= 2) {
                pnlTrai.add(l);
            } else if (cot <= 8) {
                pnlGiua.add(l);
            } else {
                pnlPhai.add(l);
            }
        }
        mainFrame.setSize(w - 1, h);
        mainFrame.setSize(w, h);
    }

    private void datVe(Ve v) {
        Ve ve = vedao.SelectByMaGhe(v.getMaGhe(), maSC);
        if (ve != null) {
            vedao.deleteGhe(v.getMaGhe(), maSC);
        } else {
            vedao.Insert(v);
        }
        loadGhe(this.maSC);
        tinhTien();
//        mainFrame.setSize(w-1,h);
//        mainFrame.setSize(w,h);

    }

    private void tinhTien() {
        int slDat = 0;
        String ghe = "Ghế: ";
        double thanhTien = 0;
        for (int i = 0; i < dsGhe.size(); i++) {
            Ve ve = dsGhe.get(i);
            if (ve.getTinhTrang() == 1) {
                slDat++;
                if (slDat > 1) {
                    ghe += ", ";
                }
                ghe += ve.getMaGhe();
                thanhTien += ve.getThanhTien();
            }
            lblSoLuong.setText("Số lượng: " + String.valueOf(slDat));
            lblGhe.setText(ghe);
            lblThanhTien.setText("Tổng tiền: " + String.valueOf(thanhTien));
        }
    }

    private void setNgayChieu() {
        try {
            String sql = "select distinct ngaychieu from suatchieu where maphim = ? and ngaychieu >= ? order by ngaychieu";
            ResultSet rs = JDBCHelper.Query(sql, this.maPhim, LocalDateTime.now());
            while (rs.next()) {
                dsNgayChieu.add(rs.getDate("ngaychieu"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatVe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSuatChieu() {
        try {
            String sql = "select * from suatchieu where maphim = ? and ngaychieu >= ? order by ngaychieu";
            ResultSet rs = JDBCHelper.Query(sql, this.maPhim, LocalDateTime.now());
            while (rs.next()) {
                SuatChieu sc = new SuatChieu();
                sc.setMaSuatChieu(rs.getInt("masuatchieu"));
                sc.setMaPhim(rs.getString("maphim"));
                sc.setNguoiTao(rs.getString("nguoitao"));
                sc.setMaPhong(rs.getString("maphong"));
                sc.setBatDau(String.valueOf(rs.getTime("batdau")));
                sc.setKetThuc(String.valueOf(rs.getTime("ketthuc")));
                sc.setNgayChieu(rs.getDate("ngaychieu"));
                dsSuatChieu.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatVe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
