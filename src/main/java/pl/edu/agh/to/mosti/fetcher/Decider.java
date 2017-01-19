package pl.edu.agh.to.mosti.fetcher;

import java.util.ArrayList;
import java.util.List;

public class Decider {

    private List<Fetcher> fetchers = new ArrayList<>();

    public void register( Fetcher fetcher ) {
        fetchers.add( fetcher );
    }

    public FetchResult decide(FetchRequest request) throws FetcherException {

        for ( Fetcher fetcher : fetchers ) {

            try {
                return fetcher.fetch(request);
            } catch ( FetcherException ex ) {
                System.out.println("Fetcher exception: " + ex.getMessage() );
            }
        }

        throw new FetcherException("All registered fetcher failed");
    }

}
