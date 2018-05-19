package tech.kikutaro.javamailsandbox;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * JavaMailによるメール送信サンプルコード.
 * 
 * @author kikuta
 */
public class JavaMailSimpleTextMain {

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", 25);

        Session session = Session.getInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        session.setDebug(true);

        try {
            msg.setSubject("こんにちは");
            msg.setText("こんにちは");
            msg.setFrom("from@example.com");
            msg.setRecipients(Message.RecipientType.TO, "to@example.com");
            msg.writeTo(System.out);
            
            Transport.send(msg, "user", "pass");
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailSimpleTextMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
