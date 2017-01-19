package pl.edu.agh.to.mosti.fetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StaticFetcher implements Fetcher {

    private ResultFormatter formatter = new ResultFormatter();

    public FetchResult fetch(FetchRequest fetchRequest) throws FetcherException {

        try
        {
            Document doc = Jsoup.connect(fetchRequest.getURL()).get();
            Elements elements = doc.select(fetchRequest.getSelector());

            String text = formatter.format( elements );

            FetchResult result = new FetchResult();
            result.setText( text );
            return result;

        } catch (IOException e) {
            throw new FetcherException("StaticFetcher IOException occurred: " +  e.getMessage());
        } catch (Exception e) {
            throw new FetcherException("StaticFetcher Exception occurred: " +  e.getMessage());
        }
    }
}

