package tech.kikutaro.commonsemailsandbox;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Apache Commons Emailによるメール送信サンプルコード.
 * 
 * @author kikuta
 */
public class CommonsEmailMain {

    public static void main(String[] args) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(25);
        email.setAuthentication("user", "pass");
        email.setSubject("こんにちは");
        email.setMsg("こんにちは");
        email.setFrom("from@example.com");
        email.addTo("to@example.com");
        email.setDebug(true);
        email.send();
    }
    
}
