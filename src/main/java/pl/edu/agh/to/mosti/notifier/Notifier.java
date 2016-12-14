package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

public class Notifier {

    /** Takes page change and notifies user using all notification methods */
    public void Notify (PageChange change) throws InvalidNotificationType {
        String message = getMessage(change.getTitle(), change.getOldValue(), change.getNewValue());

        NotificationSenderFactory senderFactory = new NotificationSenderFactory();
        //TODO: change pair to internal class to improve readability over Pair
        for(Pair notification : change.getNotificationMethods()) {
            NotificationSender sender = senderFactory.provideNotificationSender((NotificationType) notification.getKey());
            sender.sendNotification(message, (String) notification.getValue());
        }
   }

    /** Formats message to be sent out */
    private String getMessage (String title, String oldValue, String newValue) {
        return "Notification of change in  " + title + " page. " + oldValue + " is now " + newValue;
    }
}
