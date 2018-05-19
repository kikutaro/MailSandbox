package tech.kikutaro.javamailsandbox;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * GmailのSMTP(OAuth認証)によるメール送信サンプルコード.
 * 
 * @author kikuta
 */
public class JavaMailGmailMain {

    public static void main( String[] args ) throws Exception {
        Credential credential = authorize();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("こんにちは");
            msg.setContent("hello", "text/plain");
            Address from = new InternetAddress("from@example.com");
            msg.setFrom(from);
            Address to = new InternetAddress("to@example.com");
            msg.setRecipient(RecipientType.TO, to);
            Transport.send(msg, "xxxxx@gmail.com", credential.getAccessToken());
        } catch (MessagingException e) {
                System.out.println(e.getMessage());
        }
    }

    private static Credential authorize() throws Exception {
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        FileDataStoreFactory DATA_STORE_FACTORY = new FileDataStoreFactory(new File("."));
        //https://console.developers.google.com/flows/enableapi?apiid=gmail
        GoogleClientSecrets clientSecrets = 
                GoogleClientSecrets.load(
                        JSON_FACTORY, 
                        Files.newBufferedReader(Paths.get("./client_secret_xxxxxxxxxxx.apps.googleusercontent.com.json"), StandardCharsets.UTF_8)
                );
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
            Collections.singleton(GmailScopes.MAIL_GOOGLE_COM)).setDataStoreFactory(DATA_STORE_FACTORY)
            .setAccessType("offline")
            .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    } 
}
