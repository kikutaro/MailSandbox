package tech.kikutaro.commonsemailsandbox;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Apache Commons Emailによるマルチパートメール送信サンプルコード.
 * 
 * @author kikuta
 */
public class CommonsEmailMultiPartMain {

    public static void main(String[] args) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("localhost");
        email.setSmtpPort(25);
        email.setAuthentication("user", "pass");
        email.setSubject("こんにちは");
        email.setHtmlMsg("<html><body><b>こんにちは</b></body></html>");
        email.setTextMsg("こんにちは");
        email.setFrom("from@example.com");
        email.addTo("to@example.com");
        email.setDebug(true);
        email.send();
    }
    
}
