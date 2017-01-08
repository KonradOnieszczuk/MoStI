package pl.edu.agh.to.mosti.notifier;

import com.google.inject.Inject;
import javafx.util.Pair;

public class Notifier implements INotifier{
    @Inject
    SenderFactory senderFactory;

    /** Takes page change and notifies user using all notification methods */
    public void notify (NotificationRequest request) throws InvalidNotificationType {
        //TODO: change pair to internal class to improve readability over Pair
        for(Pair notification : request.getNotificationMethods()) {
            NotificationSender sender = senderFactory.provideNotificationSender((NotificationType) notification.getKey());
            sender.sendNotification(request.getPageChange(), (String) notification.getValue());
        }
   }
}
