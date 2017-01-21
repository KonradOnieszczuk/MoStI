package pl.edu.agh.to.mosti.fetcher;

interface Decider {

    void register( Fetcher fetcher );
    FetchResult decide(FetchRequest request) throws FetchException;
}
