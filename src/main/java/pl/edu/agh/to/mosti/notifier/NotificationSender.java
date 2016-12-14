package pl.edu.agh.to.mosti.notifier;

public interface NotificationSender {
    void sendNotification (String message, String address);
}
