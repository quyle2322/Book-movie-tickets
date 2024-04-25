/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import sun.swing.ImageIconUIResource;

/**
 *
 * @author LENOVO
 */
public class XImage {

    public static Image getAppIcon(File path) {
        URL url = XImage.class.getResource("CINEMA-master-20231129T121428Z-001/CINEMA-master/CINEMA-master//src");
        return new ImageIcon(url).getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
    }

    public static void save(File src) {
        File dst = new File("logos", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static  ImageIcon readNhanSu(String fileName) {
        URL url = XImage.class.getResource("/imgNhanSu/" + fileName);
        
        return new ImageIcon(url);
    }

    public static ImageIcon readPoster(String fileName) {
        URL url = XImage.class.getResource("/imgPoster/" + fileName);
        
        return new ImageIcon(url);

    }

    public static ImageIcon readTenDienVien(String fileName, int width, int height) {
        URL url = XImage.class.getResource("/imgDienVien/" + fileName+ ".png");
        
        return new ImageIcon(url);
    }
    public static ImageIcon readHinhDienVien(String fileName) {
        URL url = XImage.class.getResource("/imgDienVien/" + fileName);
        
        return new ImageIcon(url);
    }

}
