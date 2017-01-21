package pl.edu.agh.to.mosti.fetcher;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.notifier.Notifier;
import pl.edu.agh.to.mosti.notifier.NotifierInjector;

@Configuration
public class FetcherContext {

    @Autowired
    public FetcherContext( Comparator comparator, UserDataProvider provider ) {

        // TODO: this should be moved to notifier
        Injector injector = Guice.createInjector(new NotifierInjector());
        comparator.setNotifier( injector.getInstance(Notifier.class) );

        // Configure  provider
        Decider decider = new ChainDecider();
        decider.register( new StaticFetcher() );
        decider.register( new DynamicFetcher(5) );
        provider.setDecider(decider);
    }
}

