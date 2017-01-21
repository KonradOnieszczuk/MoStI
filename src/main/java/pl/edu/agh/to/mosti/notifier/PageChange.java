package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PageChange {
    private String title;
    private String url;
    private String oldValue;
    private String newValue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
