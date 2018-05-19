package tech.kikutaro.mailaddressverificationsandbox;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Apache Validatorによるメールアドレスフォーマットの検証.
 * 
 * @author kikuta
 */
public class MailAddressVerificationByApacheValidator {

    public static void main(String[] args) {
        //.abcde@example.com ドットではじまる
        boolean isValidStartDot = EmailValidator.getInstance().isValid(".abcde@example.com");
        System.out.println("ドットではじまる：" + isValidStartDot);
        //ab..cde@example.com 連続したドットが含まれている
        boolean isValidContinuedDot = EmailValidator.getInstance().isValid("ab..cde@example.com");
        System.out.println("連続したドットが含まれている：" + isValidContinuedDot);
        //abcde.@example.com アットマークの前がドット
        boolean isValidBeforeAtmarkDot = EmailValidator.getInstance().isValid("abcde.@example.com");
        System.out.println("アットマークの前がドット：" + isValidBeforeAtmarkDot);
    }
    
}
