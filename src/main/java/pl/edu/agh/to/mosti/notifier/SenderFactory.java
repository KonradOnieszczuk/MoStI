package pl.edu.agh.to.mosti.notifier;


public interface SenderFactory {
    NotificationSender provideNotificationSender(NotificationType type) throws InvalidNotificationType;
}
