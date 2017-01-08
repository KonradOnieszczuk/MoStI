package pl.edu.agh.to.mosti.notifier;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import java.util.Properties;

import static org.mockito.Mockito.mock;

public class MockTransportFactory implements TransportFactory {
    Transport transport;

    @Override
    public Transport getTransport() throws NoSuchProviderException {
        if(transport == null){
            Transport transport = mock(Transport.class);
            this.transport = transport;
        }
        return transport;
    }

    @Override
    public Session getSession() {
        Session session = Session.getDefaultInstance(new Properties());
        return session;
    }
}
