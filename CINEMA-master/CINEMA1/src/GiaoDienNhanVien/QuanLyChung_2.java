package GiaoDienNhanVien;

import cinema.QuanLyVe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class QuanLyChung_2 extends JFrame {
     private int h = 352, w = 626;
     private int showMenu_action;
     private JMenuItem itemMenu_QuanLyNhanSu, itemMenu_QuanLyXuatChieu, itemMenu_QuanLyPhim, itemMenu_QuanLyVe, itemMenu_thongke,itemMenu_Dangnhap, itemMenu_Dangki;
     private JPopupMenu pop_QuanLy, pop_ThongKe, pop_DangXUat; 
     private JLabel  lab_backGround, lab_X, lab_Cinema, lab_thongke, lab_QuanLy, lab_dangxuat, labImg_nhanvien, labTen_nhanvien, labVaitro_nhanvien;
     private JPanel panContainer_nhanvien;
     public QuanLyChung_2(){
          initComponents();
     }
     public void initComponents(){
          //Thiết kế cửa sổ
          setSize(w, h);
          setLocationRelativeTo(null);
          getContentPane().setBackground(new Color(255,240,235));
          setTitle("Giao diện quản lý chung");
          setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          setUndecorated(true);
          setLayout(null);
          
          lab_backGround = new JLabel(new ImageIcon(getClass().getResource("/img/cinema_backgroud.png")));
          lab_backGround.setBounds(0, 0, 626, 352);
          
          lab_X = new JLabel("X");
          lab_X.setOpaque(true);
          lab_X.setBackground(Color.BLACK);
          lab_X.setForeground(Color.RED);
          lab_X.setHorizontalAlignment(SwingConstants.CENTER);
          lab_X.setVerticalAlignment(SwingConstants.CENTER);
          lab_X.setFont(new Font("Arial", Font.BOLD, 45));
          lab_X.setBounds(570, 3, 50, 50);
          

          lab_X.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e){
                    System.exit(0);
               }
               public void mouseEntered(MouseEvent e){
                    lab_X.setBackground(new Color(14, 3, 140));
                    lab_X.setForeground(new Color(179, 191, 252));
               }
               public void mouseExited(MouseEvent e){
                    lab_X.setBackground(Color.BLACK);
                    lab_X.setForeground(Color.RED);
               }
          });

          lab_Cinema = new JLabel("CINEMA");
          lab_Cinema.setFont(new Font("Arial", 1, 50));
          add(lab_Cinema);
          lab_Cinema.setBounds(20, 20, 200, 50);
          lab_Cinema.setForeground(Color.WHITE);
          // lab_Cinema.setBackground(Color.WHITE);
          // lab_Cinema.setOpaque(true);
          
          lab_QuanLy = new JLabel("Quản lý");
          lab_QuanLy.setFont(new Font("Arial", 1, 25));
          lab_QuanLy.setForeground(Color.LIGHT_GRAY);
          add(lab_QuanLy);
          lab_QuanLy.setBounds(50, 110, 115, 30);
          // lab_QuanLy.setBackground(Color.BLACK);
          // lab_QuanLy.setOpaque(true);

          lab_thongke = new JLabel("Thống kê");
          lab_thongke.setFont(new Font("Arial", 1, 25));
          lab_thongke.setForeground(Color.LIGHT_GRAY);
          add(lab_thongke);
          lab_thongke.setBounds(50, 150, 140, 30);
          // lab_thongke.setBackground(Color.BLACK);
          // lab_thongke.setOpaque(true);

          lab_dangxuat = new JLabel("Đăng xuất");
          lab_dangxuat.setFont(new Font("Arial", 1, 25));
          lab_dangxuat.setForeground(Color.LIGHT_GRAY);
          add(lab_dangxuat);
          lab_dangxuat.setBounds(50, 190, 150, 30);
          // lab_dangxuat.setBackground(Color.BLACK);
          // lab_dangxuat.setOpaque(true);

          lab_QuanLy.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e){
                    showMenu_action = 1;
                    showMenu_action(showMenu_action);
               }
               public void mouseEntered(MouseEvent e){
                    if(showMenu_action != 1){
                         showMenu_action = 2;
                    }
                    showMenu_action(showMenu_action);
               }
               public void mouseExited(MouseEvent e){
                    if(showMenu_action != 1){
                         showMenu_action = 3;
                    }
                    showMenu_action(showMenu_action);
               }
          });
          lab_thongke.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e){
                    showMenu_action = 4;
                    showMenu_action(showMenu_action);
               }
               public void mouseEntered(MouseEvent e){
                    if(showMenu_action != 4){
                         showMenu_action = 5;
                    }
                    showMenu_action(showMenu_action);
               }
               public void mouseExited(MouseEvent e){
                    if (showMenu_action != 4) {
                         showMenu_action = 6;
                    }
                    showMenu_action(showMenu_action);
               }
          });
          lab_dangxuat.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e){
                    showMenu_action = 7;
                    showMenu_action(showMenu_action);
               }
               public void mouseEntered(MouseEvent e){
                    if (showMenu_action != 7) {
                         showMenu_action = 8;
                    }
                    showMenu_action(showMenu_action);
               }
               public void mouseExited(MouseEvent e){
                    if (showMenu_action != 7) {
                         showMenu_action = 9;
                    }
                    showMenu_action(showMenu_action);
               }
          });
          lab_backGround.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e){
                    lab_QuanLy.setFont(new Font("Arial", 1, 25));
                    lab_QuanLy.setForeground(Color.LIGHT_GRAY);
                    lab_dangxuat.setFont(new Font("Arial", 1, 25));
                    lab_dangxuat.setForeground(Color.lightGray);
                    lab_thongke.setFont(new Font("Arial", 1, 25));
                    lab_thongke.setForeground(Color.LIGHT_GRAY);
                    pop_QuanLy.setVisible(false);
                    showMenu_action = 0;
               }
          });

          itemMenu_QuanLyNhanSu = new JMenuItem("Quản lý nhân sự");
          itemMenu_QuanLyNhanSu.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QuanLyNhanSu.quanLiNhanSu();
              }
          });
          itemMenu_QuanLyNhanSu.setFont(new Font("Arial", 0, 15));
          itemMenu_QuanLyNhanSu.setMargin(new Insets(0, -20, 0, 0));
         
          itemMenu_QuanLyPhim = new JMenuItem("Quản lý phim");
          itemMenu_QuanLyPhim.setFont(new Font("Arial", 0, 15));
          itemMenu_QuanLyPhim.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_QuanLyPhim.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QuanLyNhanSu.quanLiPhim();
              }
          });
          
          itemMenu_QuanLyVe = new JMenuItem("Quản lý vé");
          itemMenu_QuanLyVe.setFont(new Font("Arial", 0, 15));
          itemMenu_QuanLyVe.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_QuanLyVe.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QuanLyVe().setVisible(true);
              }
          });
          
          itemMenu_QuanLyXuatChieu = new JMenuItem("Quản lý xuất chiếu");
          itemMenu_QuanLyXuatChieu.setFont(new Font("Arial", 0, 15));
          itemMenu_QuanLyXuatChieu.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_QuanLyXuatChieu.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QLSuatChieu.RunQLXC();              }
          });
          
          pop_QuanLy = new JPopupMenu();
          pop_QuanLy.add(itemMenu_QuanLyNhanSu);
          pop_QuanLy.addSeparator();
          pop_QuanLy.add(itemMenu_QuanLyPhim);
          pop_QuanLy.addSeparator();
          pop_QuanLy.add(itemMenu_QuanLyVe);
          pop_QuanLy.addSeparator();
          pop_QuanLy.add(itemMenu_QuanLyXuatChieu);
          pop_QuanLy.setPreferredSize(new Dimension(145, 100));
          
          itemMenu_thongke = new JMenuItem("Thống kê doanh thu");
          itemMenu_thongke.setFont(new Font("Arial", 0, 15));
          itemMenu_thongke.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_thongke.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new QLThongKe.RunQLThongKe();
              }
          });
          pop_ThongKe = new JPopupMenu();
          pop_ThongKe.add(itemMenu_thongke);
          pop_ThongKe.setPreferredSize(new Dimension(160, 25));

          itemMenu_Dangki = new JMenuItem("Đỏi mật khẩu");
          itemMenu_Dangki.setFont(new Font("Arial", 0, 15));
          itemMenu_Dangki.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_Dangki.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QuanLyTaiKhoan.DoiMatKhau();
              }
          });

          
          itemMenu_Dangnhap = new JMenuItem("Đăng xuất");
          itemMenu_Dangnhap.setFont(new Font("Arial", 0, 15));
          itemMenu_Dangnhap.setMargin(new Insets(0, -20, 0, 0));
          itemMenu_Dangnhap.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new QuanLyTaiKhoan.DangNhapNV();
              }
          });
          
          pop_DangXUat = new JPopupMenu();
          pop_DangXUat.add(itemMenu_Dangki);
          pop_DangXUat.addSeparator();
          pop_DangXUat.add(itemMenu_Dangnhap);
          pop_DangXUat.setPreferredSize(new Dimension(100, 50));

          panContainer_nhanvien = new JPanel();
          add(panContainer_nhanvien);
          panContainer_nhanvien.setBounds(250, 80, 330, 700);
          panContainer_nhanvien.setBackground(Color.WHITE);
          panContainer_nhanvien.setLayout(null);

          labImg_nhanvien = new JLabel();
          labImg_nhanvien.setOpaque(true);
          labImg_nhanvien.setBackground(Color.pink);
          panContainer_nhanvien.add(labImg_nhanvien);
          labImg_nhanvien.setBounds(110, 120, 120, 150);

          labTen_nhanvien = new JLabel("Tên nhân viên:");
          panContainer_nhanvien.add(labTen_nhanvien);
          labTen_nhanvien.setFont(new Font("Arial", 1, 15));
          labTen_nhanvien.setBounds(5, 5, 120, 15);

          labVaitro_nhanvien = new JLabel("Vai trò:");
          panContainer_nhanvien.add(labVaitro_nhanvien);
          labVaitro_nhanvien.setFont(new Font("Arial", 1, 15));
          labVaitro_nhanvien.setBounds(5, 25, 120, 15);

          

          add(lab_X);
          add(lab_backGround);
     }
     private void showMenu_action(int x){
          if (x == 1) {
               pop_QuanLy.show(lab_QuanLy, 120, 5);
          }
          if (x == 2) {
               lab_QuanLy.setFont(new Font("Arial", 1, 30));
               lab_QuanLy.setBounds(50, 110, 130, 35);
               lab_QuanLy.setForeground(Color.WHITE);

               lab_dangxuat.setFont(new Font("Arial", 1, 25));
               lab_dangxuat.setForeground(Color.lightGray); 

               lab_thongke.setFont(new Font("Arial", 1, 25));
               lab_thongke.setForeground(Color.LIGHT_GRAY);  

               pop_ThongKe.setVisible(false);
               pop_DangXUat.setVisible(false);
          }
          if (x == 3) {
               lab_QuanLy.setFont(new Font("Arial", 1, 25));
               lab_QuanLy.setForeground(Color.LIGHT_GRAY);
          }
          if(x == 4){
               pop_ThongKe.show(lab_thongke, 140, 5);
          }
          if(x == 5){
               lab_thongke.setForeground(Color.WHITE);
               lab_thongke.setFont(new Font("Arial", 1,30));
               lab_thongke.setBounds(50, 150, 160, 35);

               lab_QuanLy.setFont(new Font("Arial", 1, 25));
               lab_QuanLy.setForeground(Color.LIGHT_GRAY);

               lab_dangxuat.setFont(new Font("Arial", 1, 25));
               lab_dangxuat.setForeground(Color.lightGray); 

               pop_QuanLy.setVisible(false);
               pop_DangXUat.setVisible(false);
          }
          if(x == 6){
               lab_thongke.setFont(new Font("Arial", 1, 25));
               lab_thongke.setForeground(Color.LIGHT_GRAY);
          }
          if (x == 7) {
               pop_DangXUat.show(lab_dangxuat, 155, 5);
          }
          if (x == 8) {
               lab_dangxuat.setForeground(Color.WHITE); 
               lab_dangxuat.setFont(new Font("Arial", 1, 30));
               lab_dangxuat.setBounds(50, 190, 170, 35);

               lab_QuanLy.setFont(new Font("Arial", 1, 25));
               lab_QuanLy.setForeground(Color.LIGHT_GRAY);

               lab_thongke.setFont(new Font("Arial", 1, 25));
               lab_thongke.setForeground(Color.LIGHT_GRAY);

               pop_QuanLy.setVisible(false);
               pop_ThongKe.setVisible(false);
          }
          if (x == 9) {
               lab_dangxuat.setFont(new Font("Arial", 1, 25));
               lab_dangxuat.setForeground(Color.lightGray); 
          }
     }
     public static void main(String[] args) {
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Windows".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(QuanLyChung_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(QuanLyChung_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(QuanLyChung_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(QuanLyChung_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } 
          new QuanLyChung_2().setVisible(true);
     }
}
