package pl.edu.agh.to.mosti.fetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StaticFetcher implements Fetcher {

    public FetchResult fetch(FetchRequest fetchRequest) throws FetchException {

        try
        {
            Document doc = Jsoup.connect(fetchRequest.getURL()).get();
            Elements elements = doc.select(fetchRequest.getSelector());

            if ( elements.isEmpty() ) {
                throw new FetchException("StaticFetcher element not found");
            }

            FetchResult result = new FetchResult();
            result.setText(elements.first().text());
            return result;

        } catch (IOException e) {
            throw new FetchException("StaticFetcher IOException occurred: " +  e.getMessage());
        } catch (Exception e) {
            throw new FetchException("StaticFetcher Exception occurred: " +  e.getMessage());
        }
    }
}

