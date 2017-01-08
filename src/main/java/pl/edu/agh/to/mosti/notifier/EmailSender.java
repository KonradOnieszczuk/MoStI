package pl.edu.agh.to.mosti.notifier;

import com.google.inject.Inject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements NotificationSender {

    private TransportFactory emailTransportFactory;

    @Inject
    public void setTransportFactory(TransportFactory transportFactory){
        this.emailTransportFactory = transportFactory;
    }

    public void sendNotification(PageChange pageChange, String address) {

        final String username = "mosti.notification@gmail.com";
        Session session = emailTransportFactory.getSession();


        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(username));
            email.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            email.setSubject("Testing Notification");
            email.setText(getMessage(pageChange));
            Transport transport = emailTransportFactory.getTransport();
            transport.connect();
            transport.sendMessage(email, email.getAllRecipients());
            transport.close();

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /** Formats message to be sent out */
    private String getMessage (PageChange pageChange) {
        StringBuilder message = new StringBuilder("Hello,\n");
        message.append("This message is to notify you of a change in page " + pageChange.getTitle() + "\n");
        message.append("The value you observed has changed from " + pageChange.getOldValue()
                + "to " + pageChange.getNewValue() + "\n");
        message.append("To change the notification options go to the MoStI page");
        return message.toString();
    }
}