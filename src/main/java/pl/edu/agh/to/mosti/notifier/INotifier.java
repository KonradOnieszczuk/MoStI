package pl.edu.agh.to.mosti.notifier;

/**
 * Created by Limonka on 2017-01-07.
 */
public interface INotifier {
    public void notify(NotificationRequest notificationRequest) throws InvalidNotificationType;
}
