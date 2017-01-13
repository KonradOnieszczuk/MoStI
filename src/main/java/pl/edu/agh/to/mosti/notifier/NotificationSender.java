package pl.edu.agh.to.mosti.notifier;

public interface NotificationSender {
    void sendNotification (PageChange change, String address);
}
