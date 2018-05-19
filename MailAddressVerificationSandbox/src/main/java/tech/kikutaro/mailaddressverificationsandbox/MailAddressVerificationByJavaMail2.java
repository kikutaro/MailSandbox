package tech.kikutaro.mailaddressverificationsandbox;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * JavaMailによるメールアドレスフォーマットの検証.
 * 
 * @author kikuta
 */
public class MailAddressVerificationByJavaMail2 {

    public static void main(String[] args) {
        try {
            System.out.println("ドットではじまるアドレス：");
            InternetAddress ドットではじまるアドレス = new InternetAddress(".abcde@example.com");
            ドットではじまるアドレス.validate();
        } catch (AddressException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MailAddressVerificationByJavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println("連続したドットが含まれているアドレス：");
            InternetAddress 連続したドットが含まれているアドレス = new InternetAddress("ab..cde@example.com");
            連続したドットが含まれているアドレス.validate();
        } catch (AddressException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MailAddressVerificationByJavaMail2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println("アットマークの前がドット：");
            InternetAddress アットマークの前がドット = new InternetAddress("abcde.@example.com");
            アットマークの前がドット.validate();
        } catch (AddressException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MailAddressVerificationByJavaMail2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
