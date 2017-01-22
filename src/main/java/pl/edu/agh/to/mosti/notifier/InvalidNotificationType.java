package pl.edu.agh.to.mosti.notifier;

public class InvalidNotificationType extends RuntimeException {
    public InvalidNotificationType(NotificationType type) {
        super("No sender found for type " + type);
    }
}
