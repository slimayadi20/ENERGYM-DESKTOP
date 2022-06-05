/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author YESYN
 */
public class JavaMailEvent {

    public static void sendMail(String objet, String contenu, String to, String qr) throws Exception {
        try {
            String host = "smtp.gmail.com";
            String user = "slim.ayadi@esprit.tn";
            String pass = "slimayadi22";
            String from = "slim";
            String subject = "" + objet;
            String messageText = "" + contenu;

            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            //msg.setText(messageText);
            MimeBodyPart messageBodyPart = new MimeBodyPart();   //message part
            messageBodyPart.setContent(messageText, "text/html");

            Multipart multipart = new MimeMultipart();    //multipart
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachedPart = new MimeBodyPart();    //attachment part
            attachedPart.attachFile("src\\css\\" + qr + ".PNG");
            multipart.addBodyPart(attachedPart);

            msg.setContent(multipart);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}