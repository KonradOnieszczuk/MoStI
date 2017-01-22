package pl.edu.agh.to.mosti.fetcher;

interface Fetcher {
    FetchResult fetch(FetchRequest fetchRequest) throws FetchException;
}
