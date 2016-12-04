package pl.edu.agh.to.mosti.notifier;

public abstract class NotificationSender {
    abstract void sendNotification (String message, String address);

    public static NotificationSender provideNotificationSender(NotificationType type) throws InvalidNotificationType {
        if( type.equals(NotificationType.email)) {
            return new EmailSender();
        } else if (type.equals(NotificationType.hangout)){
            return new HangoutsSender();
        }
        throw new InvalidNotificationType(type);
    }
}
