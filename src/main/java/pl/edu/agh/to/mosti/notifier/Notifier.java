package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;

@Component
public class Notifier implements INotifier{

    SenderFactory senderFactory;

    @Autowired
    public Notifier(SenderFactory senderFactory){
        this.senderFactory = senderFactory;
    }

    /** Takes {@link Section} and notifies user using all requested notification methods */
    public void notify (Section section, String currentContent, String previousContent) throws InvalidNotificationType {
        System.out.println(senderFactory + "   sender factory");
        for(Notification notification : section.getNotifications()) {
            NotificationSender sender = senderFactory.provideNotificationSender(notification.getNotificationType());
            PageChange pageChange = new PageChange();
            pageChange.setTitle(section.getAlias());
            pageChange.setUrl(section.getUrl());
            pageChange.setNewValue(currentContent);
            pageChange.setOldValue(previousContent);
            sender.sendNotification(pageChange, notification.getContactInfo());
        }
   }
}
