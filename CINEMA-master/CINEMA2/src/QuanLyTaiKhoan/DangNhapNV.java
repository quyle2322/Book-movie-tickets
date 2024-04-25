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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import utils.Auth;
import utils.MsgBox;

/**
 *
 * @author ngoth
 */
public class DangNhapNV {
    NhanVienDAO dao = new NhanVienDAO();
    KhachHangDAO khdao = new KhachHangDAO();

    int h = 600, w = 1200;
    // ch: component high
    int ch = h / 12;
    int cw = w / 5;
    int x = 10, y = 10;

    JFrame mainFrame;
    JPanel mainPanel, btnPanel;
    JLabel lblTitle, lblEmail, lblPassword, lblClose, lblForget, lblright, lblleft, lblMember;
    JPasswordField txtPassword;
    JTextField txtEmail;
    JButton btnSignup, btnLogin, btnNV;

    public DangNhapNV() {
        myGui();
        btnLogin.requestFocus();
        txtEmail.setText("hungdang789@gmail.com");
        txtPassword.setText("111116");
    }

    private void myGui() {
        mainFrame = new JFrame();
        mainFrame.setSize(w, h);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());

        lblright = new JLabel();
       // lblright.setBounds(0, 0, w / 2-30 + 120, h);
        lblright.setOpaque(true);
        lblright.setBackground(new Color(255,240,235));
        lblright.setBounds(620, 0, w / 2-20, h);
        mainFrame.add(lblright);

        ImageIcon image2 = new ImageIcon(getClass().getResource("/img/1.jpg"));
        lblleft = new JLabel(image2);
      //  lblleft.setBounds(580, 0, w / 2+80 - 120, h-30);
        lblleft.setBounds(0, 0, w / 2+20, h);
        lblleft.setOpaque(true);
        lblleft.setBackground(Color.white);
        mainFrame.add(lblleft);
   
        lblClose = new JLabel("x");
        lblClose.setFont(new Font("Arial", 1, 30));
        lblClose.setBounds(cw+300, 0, 50, 50);
        lblClose.setCursor(new Cursor(12));

        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }

            public void mouseEntered(MouseEvent me) {
                lblClose.setForeground(Color.red);
            }

            public void mouseExited(MouseEvent me) {
                lblClose.setForeground(Color.black);
            }
        });
        lblright.add(lblClose);

        lblTitle = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        lblTitle.setBounds(10, 80, w / 2 + 0, ch);
        lblTitle.setFont(new Font("Arial", 1, 40));
        lblTitle.setOpaque(true);
        //lblTitle.setBackground(Color.yellow);
        lblTitle.setBackground(new Color(255,240,235));
        lblTitle.setForeground(Color.black);
        lblright.add(lblTitle);

        y += 150;
        mainPanel = new JPanel(new FlowLayout(0, 15, 25));
        mainPanel.setBounds(10, y, w / 2 -40, h / 2 - 80);
        mainPanel.setBackground(new Color(255,240,235));
       // mainPanel.setBackground(Color.yellow);
        lblright.add(mainPanel);

        ImageIcon image = new ImageIcon(getClass().getResource("/img/email.png"));
        lblEmail = new JLabel(image, JLabel.RIGHT);
        lblEmail.setPreferredSize(new Dimension(cw - 190, ch + 12));
        lblEmail.setFont(new Font("Arial", 1, 20));
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(255,240,235));
        mainPanel.add(lblEmail);

        txtEmail = new JTextField("Email");
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setBorder(new LineBorder(Color.black, 1, true));
        txtEmail.setForeground(Color.gray);
        txtEmail.setFont(new Font("Arial", 2, 18));

        txtEmail.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                txtEmail.setText("");
                txtEmail.setFont(new Font("Arial", 1, 18));
                txtEmail.setForeground(Color.black);
            }

            public void focusLost(FocusEvent fe) {
                if (txtEmail.getText().equals("")) {
                    txtEmail.setText("Email");
                    txtEmail.setFont(new Font("Arial", 2, 18));
                    txtEmail.setForeground(Color.gray);
                }
            }
        });
        txtEmail.setPreferredSize(new Dimension(cw * 2 - 20, ch));
        mainPanel.add(txtEmail);

        ImageIcon image1 = new ImageIcon(getClass().getResource("/img/lock.png"));
        lblPassword = new JLabel(image1, JLabel.RIGHT);
        lblPassword.setPreferredSize(new Dimension(cw - 190, ch + 12));
//       lblPassword.setOpaque(true);
//       lblPassword.setBackground(Color.yellow);
        mainPanel.add(lblPassword);

        txtPassword = new JPasswordField("*****");
        txtPassword.setBorder(new LineBorder(Color.black, 1, true));
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setFont(new Font("Arial", 1, 18));
        txtPassword.setForeground(Color.gray);
        txtPassword.setPreferredSize(new Dimension(cw * 2 - 20, ch));
        txtPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                txtPassword.setText("");
                txtPassword.setFont(new Font("Arial", 1, 18));
                txtPassword.setForeground(Color.black);
                
            }

            public void focusLost(FocusEvent fe) {
                if (txtPassword.getText().equals("")) {
                    txtPassword.setText("Mật khẩu");
                    txtPassword.setFont(new Font("Arial", 2, 18));
                    txtPassword.setForeground(Color.gray);
                }
            }
        });
        mainPanel.add(txtPassword);

        y += ch * 3 + 80;
        btnPanel = new JPanel();
        btnPanel.setBounds(40, y, w / 2-40, h / 3-60);
        //btnPanel.setBackground(Color.yellow);
        btnPanel.setBackground(new Color(255,240,235));
        lblright.add(btnPanel);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(cw-30, ch));
        btnLogin.setFont(new Font("Arial", 1, 18));
        btnLogin.setBackground(Color.pink);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {    
                Login();               
            }
        });
        btnPanel.add(btnLogin);

        btnSignup = new JButton("Đăng ký");
        btnSignup.setPreferredSize(new Dimension(cw-30, ch));
        btnSignup.setFont(new Font("Arial", 1, 18));
        btnSignup.setBackground(Color.pink);

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {    
                new DangKy();
                mainFrame.dispose();
            }
        });
        btnPanel.add(btnSignup);

        lblForget = new JLabel("Quên mật khẩu?", JLabel.CENTER);
        lblForget.setPreferredSize(new Dimension(35 * x, ch));
        lblForget.setFont(new Font("Arial", 2, 16));
        lblForget.setCursor(new Cursor(12));

        lblForget.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                //lblForget.setFont(new Font("Arial", 3, 17));
                lblForget.setForeground(Color.pink);
            }

            public void mouseExited(MouseEvent me) {
                lblForget.setFont(new Font("Arial", 2, 16));
                lblForget.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {
                mainFrame.dispose();
                new QMK();
            }
        });
        btnPanel.add(lblForget);

        mainFrame.setVisible(true);
    }

    private void Login() {
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());     
        KhachHang kh = khdao.SelectById(email);   
        NhanVien nv = dao.SelectById(email);  

        if(nv==null && kh == null){  //Email k ton tai 2 bang
            MsgBox.alert(null, "Email không tồn tại!");
        }
        
        else if(nv != null && kh == null){ //Email chi ton tai bang NV
           if(nv.getMatKhau().equals(password)){
               Auth.user = nv;
                if(Auth.isManager()){
                    MsgBox.alert(null, "Quản lý đăng nhập thành công!");
                }else{
                    MsgBox.alert(null, "Nhân viên đăng nhập thành công!");
                }               
                mainFrame.dispose();
                new GiaoDienNhanVien.QuanLyChung_2().setVisible(true);
           }else{
            MsgBox.alert(null, "Sai mật khẩu!");
            }
        }
        
        else if(nv == null && kh != null){ //Email chi ton tai bang KH
            if( kh.getMatKhau().equals(password)){
                Auth.KH = kh;
                MsgBox.alert(null, "Khách hàng đăng nhập thành công!");
                mainFrame.dispose();
                new GiaoDienKhachHang.HomeTest();
            }else{
                MsgBox.alert(null, "Sai mật khẩu!");
            }
        }
        else if(nv != null && kh != null){ //Email ton tai 2 bang
                mainFrame.dispose();
                new VaiTro_DN(password, nv, kh);
//            }
        }
    }
    
    private boolean Validate(){
        if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
            txtEmail.requestFocus();
            return false;
        } 
        
//        String email="\\w+@\\w+(\\.\\w+){1,2}";
//        Pattern pattern = Pattern.compile(email);
//        Matcher matcher = pattern.matcher(txtEmail.getText());
//        try{
//            if(matcher.matches()==false)
//                throw new Exception();
//        }catch (Exception e){
//             JOptionPane.showMessageDialog(null, "Email không đúng định dạng");
//             txtEmail.requestFocus();
//             return false;
//           }
        
        if(txtPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã!");
            txtPassword.requestFocus();
            return false;
        } 
        return true;
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
            java.util.logging.Logger.getLogger(DangNhapNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        new DangNhapNV();
    }    
}
