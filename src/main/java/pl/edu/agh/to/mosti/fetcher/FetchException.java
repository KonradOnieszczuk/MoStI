package pl.edu.agh.to.mosti.fetcher;

public class FetchException extends Exception {

    public FetchException(){
        super();
    }

    public FetchException(String message ){
        super( message );
    }
}
