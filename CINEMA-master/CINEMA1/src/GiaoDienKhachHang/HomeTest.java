package GiaoDienKhachHang;
import DAO.PhimDAO;
import QuanLyTaiKhoan.DoiMatKhau;
import QuanLyTaiKhoan.DoiMatKhauKH;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import entity.Phim;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.*;
import utils.Auth;
import utils.XImage;


    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */

    /**
     *
     * @author Truong
     */
    public class HomeTest {
        PhimDAO dao = new PhimDAO();
        int h =650, w=700;
        int ch = h/10;
        int cw = w/4;
        int x=10,y=10;
        JFrame mainFrame;
        JLabel lblLogo, lblSlide,lblTen1, lblTen2, lblTen3; 
        JLabel lblPhimdc, lblPoster, lblPoster1, lblPoster2,lblPoster3;
        JTextField txtFind;
        JButton btnLogin, btnReg, btnPrev, btnNext;
        JPanel headerPanel,panelleft;
        int currentIndex = 0;
        public HomeTest(){
            myHome();
            filltoForm();
            loadUser();

        }

        Timer tm;
        int z = 0;
        String[] list = {
            "/img/Slide1.jpg",              
            "/img/Slide3.png",    
            "/img/Slide4.jpg",                         
            "/img/Slide7.jpg"                   
        };


        private void myHome() {  
            mainFrame = new JFrame("Trang chủ");
            mainFrame.setSize(h,w);
            mainFrame.setBounds(0, 0, w, h);  
            mainFrame.getContentPane().setBackground(new Color(255,240,235));
            mainFrame.setLayout(new FlowLayout());
            mainFrame.setTitle("Trang chủ");
            mainFrame.setResizable(false);  // Vô hiệu hóa khả năng thay đổi kích thước cửa sổ
            mainFrame.setAutoRequestFocus(false);
            
        // Header trang chủ
            headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            FlowLayout flowLayout = (FlowLayout) headerPanel.getLayout();
            flowLayout.setHgap(20); // Khoảng cách theo chiều ngang
            headerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            headerPanel.setPreferredSize(new Dimension(650,h-600) );
            headerPanel.setBackground(new Color(255,240,235));
            lblLogo=new JLabel();
            lblLogo.setPreferredSize(new Dimension(50,25));
            lblLogo.setIcon(new ImageIcon(getClass().getResource("/img/c1.jpg")));

            txtFind = new JTextField();
            txtFind.setPreferredSize(new Dimension(300,25));
            txtFind.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Xử lý sự kiện khi người dùng nhấn Enter
                    Find();
                }
            });

            JButton searchButton = new JButton();
            searchButton.setIcon(new ImageIcon(getClass().getResource("/img/search.png")));

            searchButton.setPreferredSize(new Dimension(25,25));
            FlowLayout searchPanelLayout = new FlowLayout();
            searchPanelLayout.setHgap(0); // Đặt khoảng cách ngang là 0
            JPanel searchPanel = new JPanel(searchPanelLayout);
            searchPanel.add(txtFind);
            searchPanel.add(searchButton);
            searchButton.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    Find();
                }

            });
            btnLogin = new JButton("Login");
            btnReg = new JButton("Register");
            btnLogin.setPreferredSize(new Dimension(90,25));
            btnReg.setPreferredSize(new Dimension(90,25));
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     new QuanLyTaiKhoan.DangNhapNV();
                    mainFrame.dispose();
                }
            });
            btnReg.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new QuanLyTaiKhoan.DangKy();
                    mainFrame.dispose();
                }
            });

            headerPanel.add(lblLogo, BorderLayout.WEST);
            headerPanel.add(searchPanel);
            headerPanel.add(btnReg, BorderLayout.SOUTH);
            headerPanel.add(btnLogin, BorderLayout.SOUTH);

            //Body1 Trang chủ
            JPanel body1 = new JPanel();
            body1.setLayout(new BoxLayout(body1, BoxLayout.X_AXIS));
            body1.setSize(w, 250);

            // Thêm panelleft và panelright vào body1
            panelleft = new JPanel();
            panelleft.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelleft.setPreferredSize(new Dimension(w, 250));

            panelleft.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lblSlide = new JLabel();
            lblSlide.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 10));
            lblSlide.setPreferredSize(new Dimension(w, 230));
            panelleft.add(lblSlide);
            body1.add(panelleft);

            lblSlide.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                    SetImageSize(0);
                }
            });
                   //set a timer
            tm = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SetImageSize(z);
                    z += 1;
                    if (z >= list.length) {
                            z = 0;
                        }
                    }
                });
            tm.start(); 

            //Body2
            JPanel body2 = new JPanel();
            body2.setLayout(new BorderLayout());
            body2.setPreferredSize(new Dimension(w, 250));
            body2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lblPhimdc = new JLabel("PHIM ĐANG CHIẾU");
            lblPhimdc.setHorizontalAlignment(JLabel.CENTER);
            lblPhimdc.setVerticalAlignment(JLabel.NORTH);
            lblPhimdc.setFont(new Font("", Font.BOLD, 15));
            Border underlineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK); // You can adjust the color and thickness as needed
            lblPhimdc.setBorder(underlineBorder);

            // Tạo các JPanel mới cho mỗi cặp lblPoster và lblTen
            JPanel panelPoster1 = new JPanel(new BorderLayout());
            JPanel panelPoster2 = new JPanel(new BorderLayout());
            JPanel panelPoster3 = new JPanel(new BorderLayout());

            // Thiết lập kích thước và màu nền cho lblPoster và lblTen
            lblPoster1 = new JLabel();
            lblPoster1.setPreferredSize(new Dimension(w/3, 50));

            lblTen1 = new JLabel();
            lblTen1.setPreferredSize(new Dimension(w/3, 20));
            lblTen1.setHorizontalAlignment(JLabel.CENTER);

            // Thiết lập kích thước và màu nền cho lblPoster và lblTen
            lblPoster2 = new JLabel();
            lblPoster2.setPreferredSize(new Dimension(w/3, 50));

            lblTen2 = new JLabel();
            lblTen2.setPreferredSize(new Dimension(w/3, 20));
            lblTen2.setHorizontalAlignment(JLabel.CENTER);

            // Thiết lập kích thước và màu nền cho lblPoster và lblTen
            lblPoster3 = new JLabel();
            lblPoster3.setPreferredSize(new Dimension(w/3, 50));
    //        lblPoster3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            lblTen3 = new JLabel();
            lblTen3.setPreferredSize(new Dimension(w/3, 20));
            lblTen3.setHorizontalAlignment(JLabel.CENTER);

            JLabel[] lblTens = {lblTen1, lblTen2, lblTen3};
            JLabel[] lblPostrers = {lblPoster1,lblPoster2,lblPoster3};
            for (int i = 0; i < lblPostrers.length; i++) {
                JLabel lblPoster = lblPostrers[i];
                JLabel lblTen = lblTens[i];

                 MouseAdapter mouseAdapter = new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        lblPoster.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        lblTen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        lblPoster.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        lblTen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Access the corresponding lblTen and lblPoster associated with the clicked labels
                        String tenPhim = lblTen.getText();
                        String sql = "SELECT * FROM Phim where tenphim=?";
                        List<Phim> dsp = dao.selectBySQL(sql, tenPhim);
                        if (!dsp.isEmpty()) {
                            Phim p = dsp.get(0);
                            String maphim = p.getMaPhim();
                            if (maphim.equalsIgnoreCase(p.getMaPhim())) {
                                hienthongtinPhim(maphim);
                                mainFrame.dispose();
    //                            hienthongtinPhim(maphim);
    //                            mainFrame.dispose();
                            }
                        }}
                };

                lblPoster.addMouseListener(mouseAdapter);
                lblTen.addMouseListener(mouseAdapter);
            }

            int borderMargin = 5; // Kích thước khoảng trắng
                lblPoster1.setBorder(BorderFactory.createEmptyBorder(0, borderMargin, 0, borderMargin));
                lblPoster2.setBorder(BorderFactory.createEmptyBorder(0, borderMargin, 0, borderMargin));
                lblPoster3.setBorder(BorderFactory.createEmptyBorder(0, borderMargin, 0, borderMargin));
            // Thêm lblPoster và lblTen vào từng JPanel tương ứng
            panelPoster1.add(lblPoster1, BorderLayout.CENTER);
            panelPoster1.add(lblTen1, BorderLayout.SOUTH);

            panelPoster2.add(lblPoster2, BorderLayout.CENTER);
            panelPoster2.add(lblTen2, BorderLayout.SOUTH);

            panelPoster3.add(lblPoster3, BorderLayout.CENTER);
            panelPoster3.add(lblTen3, BorderLayout.SOUTH);

            body2.add(lblPhimdc, BorderLayout.NORTH);
            body2.add(panelPoster1, BorderLayout.WEST);
            body2.add(panelPoster2, BorderLayout.CENTER);
            body2.add(panelPoster3, BorderLayout.EAST);

            btnNext = new JButton("Next");
            btnPrev = new JButton("Prev");
            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Phim> dsp = dao.selectBySQL("SELECT * FROM Phim");
                    currentIndex = (currentIndex - 1 + dsp.size()) % dsp.size();
                    capNhatBody2();
                }
            });

            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Phim> dsp = dao.selectBySQL("SELECT * FROM Phim");
                    currentIndex = (currentIndex + 1 + dsp.size()) % dsp.size();
                    capNhatBody2();
                }
            });

            // Hiển thị    
            mainFrame.add(headerPanel);
            mainFrame.add(body1);
            mainFrame.add(body2);
            mainFrame.add(btnPrev);
            mainFrame.add(btnNext);

            mainFrame.setLocationRelativeTo(null); 
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
            mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        System.exit(0);

                    }
                });
        }

        public void SetImageSize(int i){
            if (lblSlide.getWidth() > 0 && lblSlide.getHeight() > 0) {   
                ImageIcon icon = new ImageIcon(getClass().getResource(list[i]));
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(lblSlide.getWidth(), lblSlide.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImc = new ImageIcon(newImg);
                lblSlide.setIcon(newImc);

            }
        }
        void filltoForm() {
        String sql = "SELECT * FROM Phim";
        List<Phim> dsp = dao.selectBySQL(sql);

        if (dsp.size() >= 3) {
            Phim p1 = dsp.get(0);
            lblTen1.setText(p1.getTenPhim());
            if (p1.getPoster() != null) {
                ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/img/"+p1.getPoster()));
                Image image1 = imageIcon1.getImage();
                Image newImage1 = image1.getScaledInstance(w/3, 200, Image.SCALE_SMOOTH);
                ImageIcon newImageIcon1 = new ImageIcon(newImage1);
                lblPoster1.setIcon(newImageIcon1);

            }   

            Phim p2 = dsp.get(1);
            lblTen2.setText(p2.getTenPhim());
            if (p2.getPoster() != null) {
                ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/img/"+p2.getPoster()));
                Image image2 = imageIcon2.getImage();
                Image newImage2 = image2.getScaledInstance(w/3, 200, Image.SCALE_SMOOTH);
                ImageIcon newImageIcon2 = new ImageIcon(newImage2);
                lblPoster2.setIcon(newImageIcon2);
            }

            Phim p3 = dsp.get(2);
            lblTen3.setText(p3.getTenPhim());
            if (p3.getPoster() != null) {
                    lblPoster3.setToolTipText(p3.getPoster());
                    ImageIcon imageIcon3 = new ImageIcon(getClass().getResource("/img/"+p3.getPoster()));         
                    Image image3 = imageIcon3.getImage();
                    Image newImage3 = image3.getScaledInstance(w/3, 200, Image.SCALE_SMOOTH);
                    ImageIcon newImageIcon3 = new ImageIcon(newImage3);
                    lblPoster3.setIcon(newImageIcon3);
                }
            }
        }

        void hienthongtinPhim(String maPhim){
            ThongTinPhim ttp = new ThongTinPhim();
            ttp.setTenPhim(maPhim);
            ttp.ThongTinPhim();
        }
        void hienthongtinKH(String email){
            ThongTinKH ttkh = new ThongTinKH();
            ttkh.setEmail(email);
            ttkh.filltoForm();
        }
       
        void Find(){
            String tenPhimCanTim = txtFind.getText();
            if (!tenPhimCanTim.isEmpty()) {
                String sql = "SELECT * FROM Phim where tenphim=?";
                List<Phim> dsp = dao.selectBySQL(sql, tenPhimCanTim);
                if (!dsp.isEmpty()) {
                    Phim p = dsp.get(0);
                    String maphim = p.getMaPhim();
                    if (maphim.equalsIgnoreCase(p.getMaPhim())) {
                        hienthongtinPhim(maphim);
                        mainFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phim!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy phim!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên bộ phim!");
                txtFind.requestFocus();
            }
        }

        void loadUser(){
            if(Auth.isLoginKH()){
                btnLogin.setVisible(false);
                btnReg.setVisible(false);
                JPanel headeright = new JPanel();
                JLabel lblUserInfo = new JLabel("" + Auth.KH.getTenKH());
                lblUserInfo.setPreferredSize(new Dimension(100,20));
                lblUserInfo.setHorizontalAlignment(JLabel.RIGHT);

                JMenuBar menuBar = new JMenuBar();
                JMenu fileMenu = new JMenu("User");
                ImageIcon fileIcon = new ImageIcon(getClass().getResource("/img/menu.png"));
                fileMenu.setIcon(fileIcon);     

                        // Tạo JMenuItem
                JMenuItem hoso = new JMenuItem("Xem hồ sơ");
                hoso.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String email = Auth.KH.getEmailKH();
                        hienthongtinKH(email);  
                    }
                });
               
                JMenuItem ve = new JMenuItem("Vé đã đặt");
                ve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"Chức năng đang cập nhật!");
                    }
                });
                JMenuItem doimk = new JMenuItem("Đổi mật khẩu");
                doimk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new DoiMatKhauKH();
                    }
                });
                    
                JMenuItem exit = new JMenuItem("Đăng xuất");
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Logout();
                    }
                });

                        // Thêm các JMenuItem vào JMenu
                fileMenu.add(hoso);
                fileMenu.add(ve);
                fileMenu.add(doimk);
                fileMenu.addSeparator(); // Thêm dòng phân cách
                fileMenu.add(exit);

                        // Thêm JMenu vào JMenuBar
                menuBar.add(fileMenu);
                    
                headeright.add(lblUserInfo);
                headeright.add(Box.createHorizontalStrut(15)); 
                headeright.add(menuBar);
                headerPanel.add(headeright);
            }
            else{
                btnLogin.setVisible(true);
                btnReg.setVisible(true);
            }
        }

        void Logout(){
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                Auth.Clear();
                loadUser();
            }
        }
        private void capNhatBody2() {
            String sql = "SELECT * FROM Phim";
            List<Phim> danhSachPhim = dao.selectBySQL(sql);

            int index1 = (currentIndex) % danhSachPhim.size();
            int index2 = (currentIndex + 1) % danhSachPhim.size();
            int index3 = (currentIndex + 2) % danhSachPhim.size();

            Phim p1 = danhSachPhim.get(index1);
            lblTen1.setText(p1.getTenPhim());
            setImageIcon(lblPoster1, "/img/" + p1.getPoster());

            Phim p2 = danhSachPhim.get(index2);
            lblTen2.setText(p2.getTenPhim());
            setImageIcon(lblPoster2, "/img/" + p2.getPoster());

            Phim p3 = danhSachPhim.get(index3);
            lblTen3.setText(p3.getTenPhim());
            setImageIcon(lblPoster3, "/img/" + p3.getPoster());
        }
        private void setImageIcon(JLabel label, String imagePath) {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                ImageIcon imageIcon = new ImageIcon(imageUrl);
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(w / 3, 200, Image.SCALE_SMOOTH);
                ImageIcon newImageIcon = new ImageIcon(newImage);
                label.setIcon(newImageIcon);
            } else {
                label.setIcon(null);
            }
        }
        public static void main(String[] args) {     
           new HomeTest();
        }
    }