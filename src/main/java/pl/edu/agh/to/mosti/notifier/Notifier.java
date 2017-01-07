package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

public class Notifier implements INotifier{

    /** Takes page change and notifies user using all requested notification methods */
    public void notify (NotificationRequest request) throws InvalidNotificationType {

        NotificationSenderFactory senderFactory = new NotificationSenderFactory();
        //TODO: change pair to internal class to improve readability over Pair
        for(Pair notification : request.getNotificationMethods()) {
            NotificationSender sender = senderFactory.provideNotificationSender((NotificationType) notification.getKey());
            sender.sendNotification(request.getPageChange(), (String) notification.getValue());
        }
   }
}
