package pl.edu.agh.to.mosti.notifier;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import java.util.Properties;

import static org.mockito.Mockito.mock;

public class MockTransportFactory {
    Transport transport;

    public Transport getTransport() throws NoSuchProviderException {
        if(transport == null){
            Transport transport = mock(Transport.class);
            this.transport = transport;
        }
        return transport;
    }

    public Session getSession() {
        Session session = Session.getDefaultInstance(new Properties());
        return session;
    }
}
