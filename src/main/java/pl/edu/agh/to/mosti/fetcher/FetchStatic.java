package pl.edu.agh.to.mosti.fetcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class FetchStatic extends Fetcher{
    public void fetch(PageData pageData) throws IOException, InterruptedException {
        while(true){
            Document doc = Jsoup.connect(pageData.URL).get();
            for(String s : pageData.Selectors) {
                Elements price = doc.select(s);

                for (Element elem : price) {
                    System.out.println(elem.text());
                }
            }
            TimeUnit.SECONDS.sleep(pageData.interval);
        }
    }
}

