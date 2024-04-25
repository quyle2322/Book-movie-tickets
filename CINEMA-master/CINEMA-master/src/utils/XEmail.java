/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author ngoth
 */
public class XEmail {
    
    String[] thongTin;
    int loaiEmail;
    String emailSubject;

    public XEmail(String[] thongTin, int loaiEmail) {
        this.thongTin = thongTin;
        this.loaiEmail = loaiEmail;
        if (this.loaiEmail == 1)
            this.emailSubject = "Recover Password";
    }
    
    public void guiEmail() {

        final String username = "ngothiducnhu30052003@gmail.com";
        final String password = "wnxs bslq qoip kbph";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ngothiducnhu30052003@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("nhuntdps27430@fpt.edu.vn")
            );
            message.setSubject(this.emailSubject);

            message.setContent(this.setEmail(), "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
     private String setEmail() { 
        return this.setEmailRecover();
    }

    private String setEmailRecover() {
        String emailKP = "<html>\n"
                + "    <head>\n"
                + "        <title></title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <style>\n"
                + "            #con{\n"
                + "                background-color: white;\n"
                + "                height: 600px;\n"
                + "                width: 400px;\n"
                + "                padding: 20px;\n"
                + "            }\n"
                + "            h1{\n"
                + "                font-size: 40px;\n"
                + "                color: black;\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            strong{\n"
                + "                font-size: 25px;\n"
                + "                color: black;\n"
                + "                margin-bottom: 30px;\n"
                + "            }\n"
                + "            b{\n"
                + "                color: black;\n"
                + "                font-size: 16px;\n"
                + "            }\n"
                + "            #code{\n"
                + "                background-color: white;\n"
                + "                color: black;\n"
                + "                height: 100px;\n"
                + "                width: 200px;\n"
                + "                line-height: 30px;\n"
                + "                margin: 20px auto;\n"
                + "                text-align: center;\n"
                + "                font-weight: bold;\n"
                + "                font-size: 40px;\n"
                + "            }\n"
                + "            p{\n"
                + "                color: black;\n"
                + "                \n"
                + "            }\n"
                + "\n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div id = \"con\">\n"
                + "            <h1>CINEMA</h1>\n"
                + "            <strong>Hello hotennv </strong><br>\n"
                + "            <b>Here is your recovery code, please copy it in 60 seconds</b>\n"
                + "            <p id =\"code\"> CHUOIKP </p>\n"
                + "            <p>If you are not trying to recover your password, please ignore this email. It is possible that another user entered their login information incorrectly </p>\n"
                + "\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        
        emailKP = emailKP.replace("hotennv", this.thongTin[0]);
        emailKP = emailKP.replace("CHUOIKP", this.thongTin[1]);
        return emailKP;
    }

}
