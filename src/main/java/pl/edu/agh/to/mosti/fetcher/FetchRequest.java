package pl.edu.agh.to.mosti.fetcher;

public class FetchRequest {

    private String URL;
    private String selector;
    private int interval;

    public FetchRequest() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
