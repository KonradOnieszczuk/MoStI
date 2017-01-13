package pl.edu.agh.to.mosti.notifier;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

/**
 * Created by Limonka on 2017-01-08.
 */
public class TestInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransportFactory.class).to(MockTransportFactory.class);
        bind(SenderFactory.class).to(MockSenderFactory.class);
    }
}
