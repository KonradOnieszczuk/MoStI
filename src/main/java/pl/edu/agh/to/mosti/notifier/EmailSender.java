package pl.edu.agh.to.mosti.notifier;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements NotificationSender {


    public void sendNotification(String message, String address) {

        final String username = "mosti.notification@gmail.com";
        final String password = "notificationsender";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(username));
            email.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            email.setSubject("Testing Notification");
            email.setText(message);

            Transport.send(email);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
