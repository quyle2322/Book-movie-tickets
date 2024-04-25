/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyTaiKhoan;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import utils.XEmail;

/**
 *
 * @author ngoth
 */
public class QMK {
    
    int h = 400, w = 600;
    // ch: component high
    int ch = h / 10;
    int cw = w / 4;
    int x = 10, y = 10;
    
    JFrame mainFrame;
    JLabel lblTitle,lblTitle1, lblClose, lblEmail, lblOTP,lblmain, lblbutton,lblmk,lblmk1,lblTbao;
    JTextField txtEmail, txtOTP,txtmk,txtmk1;
    JPanel fiPanel, sePanel;
    JButton btnGui,btnXacNhan,btnXacnhan1;

    public QMK() {
        myGUI();
    }
    
    private void myGUI(){
        mainFrame = new JFrame();
        mainFrame.setSize(w, h);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(new Color(255,240,235));
       // mainFrame.setUndecorated(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        // mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
               
        fiPanel = new JPanel();
        fiPanel.setBounds(10, 0, w, h);
        fiPanel.setLayout(null);
        fiPanel.setOpaque(true);
        fiPanel.setBackground(new Color(255,240,235));
        mainFrame.add(fiPanel); 
        
        lblTitle = new JLabel("QUÊN MẬT KHẨU",JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", 1, 35));
        lblTitle.setBounds(20, y, w-100, ch);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(255,240,235));
        fiPanel.add(lblTitle);
        
        lblmain = new JLabel("Email");
        lblmain.setFont(new Font("Arial", 1, 18));
        lblmain.setBounds(20, 80, cw/3, ch);
        lblmain.setOpaque(true);
        lblmain.setBackground(new Color(255,240,235));
        fiPanel.add(lblmain);
        
        txtEmail = new JTextField();
        txtEmail.setBorder(new LineBorder(Color.black, 1, true));
        txtEmail.setFont(new Font("Arial", 1, 18));
        txtEmail.setBounds(110, 80, cw*2+20, ch);
        fiPanel.add(txtEmail);
        
        btnGui = new JButton("Gửi");
        btnGui.setFont(new Font("Arial", 1, 18));
        btnGui.setBackground(Color.pink);
        btnGui.setBounds(cw*3-10, 80, 110, ch);
        btnGui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                xacNhanNV();
            }
        });
        fiPanel.add(btnGui);
        
        lblOTP = new JLabel("OTP");
        lblOTP.setFont(new Font("Arial", 1, 18));
        lblOTP.setBounds(20, 150, cw/3, ch);
        lblOTP.setOpaque(true);
        lblOTP.setBackground(new Color(255,240,235));
        fiPanel.add(lblOTP);
        
        txtOTP = new JTextField();
        txtOTP.setBorder(new LineBorder(Color.black, 1, true));
        txtOTP.setFont(new Font("Arial", 1, 18));
        txtOTP.setBounds(110, 150, cw*2+20, ch);
        fiPanel.add(txtOTP);
        
        lblTbao = new JLabel();
        lblTbao.setBounds(cw*3-10, 150, 100, ch);
        lblTbao.setBorder(new LineBorder(Color.black, 1, true));
        //fiPanel.add(lblTbao);
        
        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setBounds(160, 220, cw, ch);
        btnXacNhan.setBackground(Color.pink);
        btnXacNhan.setFont(new Font("Arial", 1, 18));
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                xacNhan();
            }
        });
        fiPanel.add(btnXacNhan);
        
        sePanel = new JPanel();
        sePanel.setBounds(10, 0, w, h);
        sePanel.setBackground(new Color(255,240,235));
        sePanel.setLayout(null);
                
        lblTitle1 = new JLabel("QUÊN MẬT KHẨU",JLabel.CENTER);
        lblTitle1.setFont(new Font("Arial", 1, 35));
        lblTitle1.setBounds(20, y, w-100, ch);
        lblTitle1.setOpaque(true);
        lblTitle1.setBackground(new Color(255,240,235));
        sePanel.add(lblTitle1);
        
        lblmk = new JLabel("Mật khẩu");
        lblmk.setFont(new Font("Arial", 1, 18));
        lblmk.setBounds(20, 80, cw/3+30, ch);
        lblmk.setOpaque(true);
        lblmk.setBackground(new Color(255,240,235));
        sePanel.add(lblmk);
        
        txtmk = new JTextField();
        txtmk.setFont(new Font("Arial", 1, 18));
        txtmk.setBorder(new LineBorder(Color.black, 1, true));
        txtmk.setBounds(110, 80, cw*2+20, ch);
        sePanel.add(txtmk);
        
        lblmk1 = new JLabel("Xác nhận");
        lblmk1.setFont(new Font("Arial", 1, 18));
        lblmk1.setBounds(20, 150, cw/3+30, ch);
        lblmk1.setOpaque(true);
        lblmk1.setBackground(new Color(255,240,235));
        sePanel.add(lblmk1);
        
        txtmk1 = new JTextField();
        txtmk1.setFont(new Font("Arial", 1, 18));
        txtmk1.setBorder(new LineBorder(Color.black, 1, true));
        txtmk1.setBounds(110, 150, cw*2+20, ch);
        sePanel.add(txtmk1);
        
        lblTbao = new JLabel();
        lblTbao.setBounds(cw*3-10, 150, 110, ch);
        lblTbao.setBorder(new LineBorder(Color.black, 1, true));
        //sePanel.add(lblTbao);
        
        btnXacnhan1 = new JButton("Xác Nhận");
        btnXacnhan1.setFont(new Font("Arial", 1, 18));
        btnXacnhan1.setBackground(Color.pink);
        btnXacnhan1.setBounds(160, 220, cw, ch);
        btnXacnhan1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doiMK();
            }
        });
        sePanel.add(btnXacnhan1);
        
        mainFrame.add(sePanel);        
        mainFrame.setVisible(true);
    }
    
    
    Thread t;
    String maXacThuc = "";
        //////////////////  NV  //////////////////////////
    private void guiMa(NhanVien nv) {
        if (nv != null) {
            for (int i = 0; i < 6; i++) {
               ThreadLocalRandom random = ThreadLocalRandom. current(); 
               int rand = random. nextInt(1, 10);
                
                maXacThuc+=rand;
            }
            String[] thongTin = {nv.getEmailNV(), maXacThuc};
            XEmail mail = new XEmail(thongTin, 1);
            mail.guiEmail();
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 9; i >= 0; i--) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {}
                        String text = "Bạn còn "+ String.valueOf(i) + " giây";
                        lblTbao.setText(text);
                        if (i == 0) {
                           lblTbao.setText("OTP k hieu luc");
                            break;
                        }
                    }
                }
            });
            t.start();
        }
    }
        
    private void guiMa(KhachHang kh) {
        if (kh != null) {
            for (int i = 0; i < 6; i++) {
               ThreadLocalRandom random = ThreadLocalRandom. current(); 
               int rand = random. nextInt(1, 10);
                
                maXacThuc+=rand;
            }
            String[] thongTin = {kh.getEmailKH(), maXacThuc};
            XEmail mail = new XEmail(thongTin, 1);
            mail.guiEmail();
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 59; i >= 0; i--) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {}
                        String text = "Bạn còn " + String.valueOf(i) + " giây";
                         lblTbao.setText(text);
                        if (i == 0) {
                            ;
                            break;
                        }
                    }
                }
            });
            t.start();
        }
    }
         
        KhachHangDAO khdao = new KhachHangDAO();
        NhanVienDAO dao = new NhanVienDAO();
        int mode=0;
        private void xacNhanNV() {
        String email = txtEmail.getText();
        
        KhachHang kh = khdao.SelectById(email);
        NhanVien nv = dao.SelectById(email);
        
        if (nv == null && kh == null) {
            JOptionPane.showMessageDialog(null,"Email không đúng");           
        }
        
        if(nv != null && kh == null){ //Email chi ton tai bang NV
            guiMa(nv);
            mode=1;
        }
        
        if(nv == null && kh != null){
            guiMa(kh);
            mode=2;
        }       
    }

    private void xacNhan() {
        String maXacNhan = txtOTP.getText();
        
        if (maXacNhan.equals("")) {
            JOptionPane.showMessageDialog(null,"Mã xác thực không đúng");
        } else {
            if (maXacNhan.equals(maXacThuc)) {
                fiPanel.setVisible(false);
                sePanel.setVisible(true);
                t.stop();
                JOptionPane.showMessageDialog(null,"Mời bạn đổi mật khẩu");
            } else {
                JOptionPane.showMessageDialog(null,"Mã xác nhận không đúng");
            }
        }
    }
    
    private void doiMK() {
        String mk1 = txtmk.getText();
        String mk2 = txtmk1.getText();

        if (mk1.equals("") || mk2.equals("")) {
            JOptionPane.showMessageDialog(null,"Xin điền đầy đủ mật khẩu!!");
        } else {
            if (!mk1.equals(mk2)) {
                JOptionPane.showMessageDialog(null,"Mật khẩu xác thực không đúng!!");
            } else {
                if(mode==1)
                {
                    NhanVien nv = dao.SelectById(txtEmail.getText());
                    nv.setMatKhau(mk1);
                    dao.Update(nv);
                    JOptionPane.showMessageDialog(null, "Đã đổi mật khẩu thành công!!");
                }
                 if(mode==2)
                {
                    KhachHang kh = khdao.SelectById(txtEmail.getText());
                    kh.setMatKhau(mk1);
                    khdao.Update(kh);
                    JOptionPane.showMessageDialog(null, "Đã đổi mật khẩu thành công!!");
                }
                mainFrame.dispose();
            }
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
            java.util.logging.Logger.getLogger(QMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        new QMK();
    }
}
