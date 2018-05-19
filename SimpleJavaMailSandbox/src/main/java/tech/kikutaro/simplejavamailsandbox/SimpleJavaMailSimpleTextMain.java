package tech.kikutaro.simplejavamailsandbox;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

/**
 *
 * @author kikuta
 */
public class SimpleJavaMailSimpleTextMain {

    public static void main(String[] args) throws AddressException, UnsupportedEncodingException {
        Mailer mailer = MailerBuilder
                .withSMTPServer("localhost", 25, "user", "pass")
                .withDebugLogging(true)
                .buildMailer();
        
        Email email = EmailBuilder.startingBlank()
                .from("from@example.com")
                .to("to@example.com")
                .withSubject("こんにちは")
                .withPlainText("こんにちは")
                .withHTMLText("<html><body><p>こんにちは</p></body></html>")
                .buildEmail();
        
        mailer.sendMail(email);
    }
    
}
