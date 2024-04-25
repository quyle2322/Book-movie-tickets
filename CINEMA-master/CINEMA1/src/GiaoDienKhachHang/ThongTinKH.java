package GiaoDienKhachHang;
import DAO.KhachHangDAO;
import entity.KhachHang;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import javax.swing.border.EmptyBorder;
import java.util.List;
import utils.Auth;
public class ThongTinKH extends JDialog {

    private JLabel lblEmail, lblTen, lblSdt, lblDiaChi, lblTitle;
    private JTextField txtEmail, txtTen, txtSdt, txtDiaChi;
    JDialog mainFrame;
    JButton btnUpdate;
    String email;
   public ThongTinKH() {
    MyInfor();
    filltoForm();
    txtEmail.setEditable(false);
    btnUpdate.setEnabled(false);
    
}
   
   public void setEmail(String Email){
       this.email=Email;
       System.out.println(email);
   }
    void MyInfor() {
        mainFrame = new JDialog();
        mainFrame.setTitle("Thông tin cá nhân");
        mainFrame.setSize(500, 350);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(new Color(255, 240, 235));
        mainFrame.setAutoRequestFocus(false);
        
        lblTitle = new JLabel("THÔNG TIN CÁ NHÂN");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setVerticalAlignment(JLabel.NORTH);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel body = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
        body.setPreferredSize(new Dimension(400, 200));
        body.setBorder(new EmptyBorder(0, 10, 10, 0));
        body.setBackground(new Color(255, 240, 235));
        lblEmail = new JLabel("Email: ");
        lblEmail.setPreferredSize(new Dimension(50, 30));

        lblTen = new JLabel("Tên: ");
        lblTen.setPreferredSize(new Dimension(50, 30));

        lblSdt = new JLabel("SĐT: ");
        lblSdt.setPreferredSize(new Dimension(50, 30));

        lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setPreferredSize(new Dimension(50, 30));

        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(400, 30));

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(400, 30));

        txtSdt = new JTextField();
        txtSdt.setPreferredSize(new Dimension(400, 30));

        txtTen = new JTextField();
        txtTen.setPreferredSize(new Dimension(400, 30));

        btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (btnUpdate.isEnabled()) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật lại thông tin?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                Update();
                }
            }
        }
    });
        txtDiaChi.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnUpdate.setEnabled(true);
            }
        });

        txtSdt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnUpdate.setEnabled(true);
            }
        });

        txtTen.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnUpdate.setEnabled(true);
            }
        });
        body.add(lblEmail);
        body.add(txtEmail);
        body.add(lblTen);
        body.add(txtTen);
        body.add(lblSdt);
        body.add(txtSdt);
        body.add(lblDiaChi);
        body.add(txtDiaChi);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnUpdate);
        buttonPanel.setBackground(new Color(255, 240, 235));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        mainFrame.add(lblTitle, BorderLayout.NORTH);
        mainFrame.add(body, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        
        mainFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
    KhachHangDAO dao = new KhachHangDAO();
    void filltoForm(){
        String sql ="select * from khachhang where email=?";
        List<KhachHang> dskh = dao.selectBySQL(sql,email);
        
        for(KhachHang kh: dskh){
            txtDiaChi.setText(kh.getDiaChi());
            txtEmail.setText(kh.getEmailKH());
            txtSdt.setText(kh.getSoDT());
            txtTen.setText(kh.getTenKH());
        }
        
    }
    KhachHang getForm(){
        KhachHang kh = new KhachHang();
        kh.setEmailKH(txtEmail.getText());
        kh.setMatKhau(Auth.KH.getMatKhau());
        kh.setThongTinTT(kh.getThongTinTT());
        kh.setTrangThai(true);
        kh.setDiaChi(txtDiaChi.getText());
        kh.setSoDT(txtSdt.getText());
        kh.setTenKH(txtTen.getText());
        return kh;
    }
    void Update() {
    try {
        KhachHang kh = this.getForm();
        dao.Update(kh);
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public static void main(String[] args) {
        
        new ThongTinKH();
        
    }
}
