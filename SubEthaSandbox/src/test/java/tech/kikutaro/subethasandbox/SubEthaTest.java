package tech.kikutaro.subethasandbox;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.subethamail.wiser.Wiser;

/**
 * SubEtha SMTPを利用したユニットテスト.
 * 
 * https://github.com/voodoodyne/subethasmtp
 * 
 * @author kikuta
 */
public class SubEthaTest {
    
    private static Wiser wiser;

    @BeforeClass
    public static void setUpClass() {
        wiser = new Wiser(25);
        wiser.setHostname("localhost");
        wiser.start();
    }
    
    @AfterClass
    public static void tearDownClass() {
        wiser.stop();
    }
    
    @Test
    public void sendMailTest() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", 25);
        
        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        
        try {
            msg.setSubject("こんにちは");
            msg.setText("こんにちは");
            msg.setFrom("from@example.com");
            msg.setRecipients(Message.RecipientType.TO, "to@example.com");
            
            Transport.send(msg, "user", "pass");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        
        wiser.getMessages().stream().forEach(m -> {
            try {
                assertThat(m.getMimeMessage().getSubject(), Is.is("こんにちは"));
                assertThat(m.getMimeMessage().getContent().toString(), Is.is("こんにちは"));
                assertThat(m.getMimeMessage().getFrom()[0].toString(), Is.is("from@example.com"));
                assertThat(m.getMimeMessage().getRecipients(Message.RecipientType.TO)[0].toString(), Is.is("to@example.com"));               
            } catch (MessagingException | IOException ex) {
                Logger.getLogger(SubEthaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
