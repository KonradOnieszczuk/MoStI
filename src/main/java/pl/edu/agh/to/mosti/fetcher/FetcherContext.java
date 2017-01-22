package pl.edu.agh.to.mosti.fetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FetcherContext {

    @Autowired
    public FetcherContext( UserDataProvider provider ) {

        // Configure  provider
        Decider decider = new ChainDecider();
        decider.register( new StaticFetcher() );
        decider.register( new DynamicFetcher(5) );
        provider.setDecider(decider);
    }
}

