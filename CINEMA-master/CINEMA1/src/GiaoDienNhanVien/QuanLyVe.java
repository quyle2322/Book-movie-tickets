package cinema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import control.listenMouseQuanLyVe.listenQuanLyVe;
import entity.Ve;

public class QuanLyVe extends JFrame{
//     private JPanel panPhim;
//     private JScrollPane scrPhim, scrPhim_table;
//     private JTable tblPhim;
//     private DefaultTableModel mod_tbl_Phim;
//     public JPanel panContainerItem_Phim, panIMG_Phim;
//     private JLabel labPhim;
//     int h = 600, w = 1200;
//     int ch = h / 12;
//     int cw = w / 5;
//     int x = 10, y = 10;
//          
//     public QuanLyVe(){
//          initComponents();
//     }
//     
//     private void initComponents() {
//          //Thiết kế cửa sổ
//          setSize(w, h);
//          setLocationRelativeTo(null);
//          getContentPane().setBackground(new Color(255,240,235));
//          setTitle("Giao diện quản lý vé đặt");
//          setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
//          setDefaultCloseOperation(EXIT_ON_CLOSE);
//          SpringLayout layout_container = new SpringLayout();
//          setLayout(layout_container);
//          
//          //Thêm components
//          panPhim = new JPanel();
//          panPhim.setPreferredSize(new Dimension(w, h/3));
//          panPhim.setBackground(Color.WHITE);
//
//          scrPhim = new JScrollPane(panPhim);
//          scrPhim.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//          add(scrPhim);
//          layout_container.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrPhim, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
//          layout_container.putConstraint(SpringLayout.WEST, scrPhim, 10, SpringLayout.WEST, getContentPane());
//          layout_container.putConstraint(SpringLayout.NORTH, scrPhim, 10, SpringLayout.NORTH, getContentPane());
//          layout_container.putConstraint(SpringLayout.SOUTH, scrPhim, h/2, SpringLayout.NORTH, scrPhim);
//
//          //thêm ItemPhim
//          panPhim.setLayout(null);
//          int x = 10;
//          int y = 10;
//          int wr = 200;
//          for(int i = 0; i <= 100; i++){
//               panPhim.setPreferredSize(new Dimension(wr, h/3));
//               wr += 200;
//               panPhim.add(item(("Phim " + i), x, y, 200));
//               x += 200;
//               
//          }
//
//          mod_tbl_Phim = new DefaultTableModel(new String[]{"STT", "Tên Phim", "Số lượng vé", "Ngày"}, 100);
//          tblPhim = new JTable(mod_tbl_Phim);
//          scrPhim_table = new JScrollPane(tblPhim);
//          add(scrPhim_table);
//          layout_container.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrPhim_table, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
//          layout_container.putConstraint(SpringLayout.NORTH, scrPhim_table, 10, SpringLayout.SOUTH, scrPhim);
//          layout_container.putConstraint(SpringLayout.WEST, scrPhim_table, 10, SpringLayout.WEST, getContentPane());
//          layout_container.putConstraint(SpringLayout.SOUTH, scrPhim_table, -10, SpringLayout.SOUTH, getContentPane());
//
//
//     }
//     
//     private JPanel item(String tenPhim, int x, int y, int w){
//          
//          panContainerItem_Phim = new JPanel();
//          panContainerItem_Phim.setBackground(null);
//          panContainerItem_Phim.setBounds(x, 10,w, (h/2)-20);
//          panContainerItem_Phim.setLayout(null);
//          panIMG_Phim = new JPanel();
//          panIMG_Phim.setBounds(0, 0, 180,((h/2)-10) -70 );
//          panIMG_Phim.setBackground(Color.PINK);
//          labPhim = new JLabel();
//          labPhim.setFont(new Font("Arial", 0, 20));
//          labPhim.setText(tenPhim);
//          labPhim.setBounds(0, (h/2)-70, 190, 30);
//          labPhim.setOpaque(true);
//          labPhim.setBackground(null);
//          labPhim.setVerticalAlignment(SwingConstants.TOP);
//          labPhim.setHorizontalAlignment(SwingConstants.CENTER);
//          panContainerItem_Phim.add(panIMG_Phim);
//          panContainerItem_Phim.add(labPhim);
//          panIMG_Phim.addMouseListener(new MouseAdapter() {
//               public void mouseClicked(MouseEvent e){
//                    listenQuanLyVe.getListenQuanLyVe().xemve_quanLyVe();
//               }
//          });
//          return panContainerItem_Phim;
//     }
//     
     public static void main(String[] args) {
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Windows".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(QuanLyVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(QuanLyVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(QuanLyVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(QuanLyVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } 
          new QuanLyVe().setVisible(true);
     }
}
