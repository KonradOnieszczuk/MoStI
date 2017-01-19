package pl.edu.agh.to.mosti.fetcher;

public class FetcherException extends Exception {

    public FetcherException(){
        super();
    }

    public FetcherException( String message ){
        super( message );
    }
}
