package tech.kikutaro.greenmailsandbox;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 * GreenMailを利用したユニットテスト.
 * 
 * http://www.icegreen.com/greenmail/
 * 
 * @author kikuta
 */
public class GreenMailTest {
    
    @Rule
    public GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);

    @Test
    public void sendMailTest() throws MessagingException, IOException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", 3025);

        Session session = Session.getInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        session.setDebug(true);

        try {
            msg.setSubject("こんにちは");
            msg.setText("hello");
            msg.setFrom("from@example.com");
            msg.setRecipients(Message.RecipientType.TO, "to@example.com");
            msg.writeTo(System.out);
            
            Transport.send(msg, "user", "pass");
        } catch (MessagingException ex) {
            Logger.getLogger(GreenMailTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage m = emails[0];
        assertThat(m.getSubject(), Is.is("こんにちは"));
        assertThat(GreenMailUtil.getBody(m), Is.is("hello"));
        assertThat(m.getFrom()[0].toString(), Is.is("from@example.com"));
        assertThat(m.getRecipients(Message.RecipientType.TO)[0].toString(), Is.is("to@example.com"));
    }
}
