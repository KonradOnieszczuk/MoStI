package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

public class Notifier {

    /** Takes page change and notifies user using all notification methods */
    public void Notify (PageChange change) {

    }

    /** Formats message to be sent out */
    private String getMessage (String title, String oldValue, String newValue) {
        return "Notification of change in  " + title + " page. " + oldValue + " is now " + newValue;
    }
}