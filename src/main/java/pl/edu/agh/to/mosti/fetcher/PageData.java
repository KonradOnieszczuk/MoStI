package pl.edu.agh.to.mosti.fetcher;
import java.util.List;

public class PageData {
    public String URL;
    public List <String> Selectors;
    public int interval;

    public PageData(String url, List <String> selectors, int interval){
        this.URL = url;
        this.interval = interval;
        this.Selectors = selectors;
    }
}
