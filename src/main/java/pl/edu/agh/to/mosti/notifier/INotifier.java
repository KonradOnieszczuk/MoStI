package pl.edu.agh.to.mosti.notifier;

public interface INotifier {
    public void notify(NotificationRequest notificationRequest) throws InvalidNotificationType;
}
