package tech.kikutaro.javamailsandbox;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * JavaMailによるマルチパートメール送信サンプルコード.
 * 
 * @author kikuta
 */
public class JavaMailMultiPartMain {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", 25);

        Session session = Session.getInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        session.setDebug(true);
        
        try {
            MimeBodyPart text = new MimeBodyPart();
            text.setText("こんにちは");
            
            MimeBodyPart html = new MimeBodyPart();
            html.setText("<html><body><b>こんにちは</b></body></html>", "UTF-8", "html");
            
            MimeMultipart mp = new MimeMultipart("alternative", text, html);
            msg.setContent(mp);
            
            msg.setSubject("こんにちは");
            msg.setFrom("from@example.com");
            
            msg.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress("to@example.com")});
            Transport.send(msg, "user", "pass");
            
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailMultiPartMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
