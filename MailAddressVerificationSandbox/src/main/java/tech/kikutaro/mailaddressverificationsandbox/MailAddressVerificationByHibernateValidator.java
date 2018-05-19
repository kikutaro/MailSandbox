package tech.kikutaro.mailaddressverificationsandbox;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import tech.kikutaro.hibernate.model.MailModel;

/**
 * Bean Validation(Hibernate Validator)によるメールアドレスフォーマットの検証.
 * 
 * @author kikuta
 */
public class MailAddressVerificationByHibernateValidator {

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
       
        //.abcde@example.com ドットではじまる
        MailModel startDot = new MailModel(".abcde@example.com");
        Set<ConstraintViolation<MailModel>> startDotValidate = validator.validate(startDot);
        System.out.println("ドットではじまる：");
        startDotValidate.stream().forEach(v -> System.out.println(v.getMessage()));

        //ab..cde@example.com 連続したドットが含まれている
        MailModel continuedDot = new MailModel("ab..cde@example.com");
        Set<ConstraintViolation<MailModel>> continuedDotValidate = validator.validate(continuedDot);
        System.out.println("連続したドットが含まれている：");
        continuedDotValidate.stream().forEach(v -> System.out.println(v.getMessage()));
        
        //abcde.@example.com アットマークの前がドット
        MailModel beforeAtmarkDot = new MailModel("abcde.@example.com");
        Set<ConstraintViolation<MailModel>> beforeAtmarkDotValidate = validator.validate(beforeAtmarkDot);
        System.out.println("アットマークの前がドット：");
        beforeAtmarkDotValidate.stream().forEach(v -> System.out.println(v.getMessage()));
    }
    
}
