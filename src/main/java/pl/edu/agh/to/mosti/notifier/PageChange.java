package pl.edu.agh.to.mosti.notifier;

public class PageChange {
    private String title;

    public PageChange(String title, String url, String oldValue, String newValue) {
        this.title = title;
        this.url = url;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    private String url;
    private String oldValue;
    private String newValue;

    public String getTitle() {
        return title;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public String getUrl() {
        return url;
    }
}
