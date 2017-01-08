package pl.edu.agh.to.mosti.notifier;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;


public interface TransportFactory {
    Transport getTransport() throws NoSuchProviderException;
    Session getSession();
}
