package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Notifier {
    private Map<NotificationType, NotificationSender> NotificationSenders = new HashMap<>();

    /** Takes page change and notifies user using all notification methods */
    public void Notify (PageChange change){
        String message = getMessage(change.getTitle(), change.getOldValue(), change.getNewValue());

        //TODO: change pair to internal class to improve readability over Pair
        for(Pair notification : change.getNotificationMethods()) {
            NotificationSender sender = getNotificationSender((NotificationType) notification.getKey());
            sender.sendNotification(message, (String) notification.getValue());
        }
    }

    /** Formats message to be sent out */
    private String getMessage (String title, String oldValue, String newValue) {
        return "Notification of change in  " + title + " page. " + oldValue + " is now " + newValue;
    }

    /** Returns appropriate NotificationSender for each notification type. Creates new sender on first use and reuses
     * sender objects later. */
    private NotificationSender getNotificationSender (NotificationType type){
        if(NotificationSenders.containsKey(type)) {
            return NotificationSenders.get(type);
        } else {
            try {
                NotificationSender newSender = NotificationSender.provideNotificationSender(type);
                NotificationSenders.put(type, newSender);
                return newSender;
            } catch (InvalidNotificationType invalidNotificationType) {
                //TODO propagate to Notify
                invalidNotificationType.printStackTrace();
            }
            //TODO propagate error
            return null;
        }
    }
}
