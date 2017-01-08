package pl.edu.agh.to.mosti.notifier;

import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

public class EmailTransportFactory implements TransportFactory {
    private Session session;
    private Transport transport;

    public Session getSession() {

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
        this.session = session;
        return session;
    }

    public Transport getTransport() throws NoSuchProviderException {
        if (session == null) {
            this.session = getSession();
        }
        this.transport = session.getTransport("smtp");
        return transport;
    }
}
