package pl.edu.agh.to.mosti.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;

@Component
public class Notifier implements INotifier{

    private NotificationSenderFactory senderFactory;

    @Autowired
    public void setSenderFactory(NotificationSenderFactory senderFactory) {
        this.senderFactory = senderFactory;
    }

    /** Takes {@link Section} and notifies user using all requested notification methods */
    public void notify (Section section, String currentContent, String previousContent) {
        System.out.println(senderFactory + "   sender factory");
        for(Notification notification : section.getNotifications()) {
            NotificationSender sender = senderFactory.provideNotificationSender(notification.getNotificationType());
            PageChange pageChange = new PageChange(section.getAlias(), section.getUrl(), previousContent, currentContent);
            sender.sendNotification(pageChange, notification.getContactInfo());
        }
   }
}
