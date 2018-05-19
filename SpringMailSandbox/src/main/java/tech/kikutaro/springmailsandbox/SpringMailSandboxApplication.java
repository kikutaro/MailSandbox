package tech.kikutaro.springmailsandbox;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringMailSandboxApplication {
    
    @Autowired
    JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(SpringMailSandboxApplication.class, args);
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @GetMapping("/send")
    public String send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("from@example.com");
        message.setTo("to@example.com");
        message.setSubject("こんにちは");
        message.setText("こんにちは");
        javaMailSender.send(message);
        return "メールの送信を完了しました。";
    }
    
    @GetMapping("/multipart")
    public String sendMultipart() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);
        mimeHelper.setFrom("from@example.com");
        mimeHelper.setTo("to@example.com");
        mimeHelper.setSubject("こんにちは");
        mimeHelper.setText("こんにちは", "<html><body><b>こんにちは</b></body></html>");
        javaMailSender.send(mimeMessage);
        return "メールの送信を完了しました。";
    }
}
