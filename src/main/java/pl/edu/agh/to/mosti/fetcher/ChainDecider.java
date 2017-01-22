package pl.edu.agh.to.mosti.fetcher;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChainDecider implements Decider {

    private List<Fetcher> fetchers = new ArrayList<>();

    public void register( Fetcher fetcher ) {
        fetchers.add( fetcher );
    }

    public FetchResult decide(FetchRequest request) throws FetchException {

        for ( Fetcher fetcher : fetchers ) {

            try {
                return fetcher.fetch(request);
            } catch ( FetchException ex ) {
                System.out.println("Fetcher exception: " + ex.getMessage() );
            }
        }

        throw new FetchException("All registered fetcher failed");
    }
}
