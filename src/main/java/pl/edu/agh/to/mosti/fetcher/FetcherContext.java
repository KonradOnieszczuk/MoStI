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
    public FetcherContext(Comparator comparator) {
        Injector injector = Guice.createInjector(new NotifierInjector());
        comparator.setNotifier( injector.getInstance(Notifier.class) );
    }
}

