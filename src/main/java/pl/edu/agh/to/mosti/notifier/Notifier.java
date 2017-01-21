package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Notifier implements INotifier{

    @Autowired
    NotificationSenderFactory senderFactory;

    /** Takes {@link NotificationRequest} and notifies user using all requested notification methods */
    public void notify (NotificationRequest request) throws InvalidNotificationType {
        System.out.println(senderFactory + "   sender factory");
        for(Pair notification : request.getNotificationMethods()) {
            NotificationSender sender = senderFactory.provideNotificationSender((NotificationType) notification.getKey());
            sender.sendNotification(request.getPageChange(), (String) notification.getValue());
        }
   }
}
