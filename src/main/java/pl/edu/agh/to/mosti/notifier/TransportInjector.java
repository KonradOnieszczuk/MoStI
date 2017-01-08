package pl.edu.agh.to.mosti.notifier;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * Created by Limonka on 2017-01-08.
 */
public class TransportInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransportFactory.class).to(EmailTransportFactory.class);
    }
}
