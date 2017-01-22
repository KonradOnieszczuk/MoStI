package pl.edu.agh.to.mosti.fetcher;

import org.jsoup.select.Elements;

public class ResultFormatter {

    public String format( Elements elements ) {

        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < elements.size() - 1; ++i ){
            sb.append( elements.get(i).text() );
            sb.append( "," );
        }

        sb.append( elements.get( elements.size() - 1 ).text());

        return sb.toString();
    }
}
