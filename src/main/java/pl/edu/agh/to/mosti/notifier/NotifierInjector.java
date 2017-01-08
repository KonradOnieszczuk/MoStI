package pl.edu.agh.to.mosti.notifier;

import com.google.inject.AbstractModule;


public class NotifierInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(SenderFactory.class).to(NotificationSenderFactory.class);
        bind(TransportFactory.class).to(EmailTransportFactory.class);
    }
}
