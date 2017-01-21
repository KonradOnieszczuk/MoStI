package pl.edu.agh.to.mosti.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationSenderFactory implements SenderFactory {

    private List<NotificationSender> notificationSenders;

    @Autowired
    public void setNotificationSenders(List<NotificationSender> notificationSenders){
        this.notificationSenders = notificationSenders;
    }

    public NotificationSender provideNotificationSender(NotificationType type) throws InvalidNotificationType {

        for( NotificationSender sender : notificationSenders ){
            if(sender.getSupportedType().equals(type)){
                return sender;
            }
        }
        throw new InvalidNotificationType(type);
    }
}
