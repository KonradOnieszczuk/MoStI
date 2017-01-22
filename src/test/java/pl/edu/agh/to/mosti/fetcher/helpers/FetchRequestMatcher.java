package pl.edu.agh.to.mosti.fetcher.helpers;

import org.mockito.ArgumentMatcher;
import pl.edu.agh.to.mosti.fetcher.FetchRequest;


public class FetchRequestMatcher extends ArgumentMatcher<FetchRequest> {

    private FetchRequest request;

    public FetchRequestMatcher(FetchRequest request){
        this.request = request;
    }

    @Override
    public boolean matches(Object o) {
        FetchRequest req = (FetchRequest)o;
        return ( req.getURL() == request.getURL() &&
                req.getInterval() == request.getInterval() &&
                req.getSelector() == request.getSelector() );
    }
}
