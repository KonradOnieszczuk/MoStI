package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

import java.util.List;

public class NotificationRequest {
    private final PageChange pageChange;
    private final List<Pair<NotificationType, String>> notificationMethods;   //String is address or identifier used to
    // send

    public NotificationRequest(PageChange pageChange, List<Pair<NotificationType, String>> notificationMethods) {
        this.pageChange = pageChange;
        this.notificationMethods = notificationMethods;
    }

    public PageChange getPageChange() {
        return pageChange;
    }
    public List<Pair<NotificationType, String>> getNotificationMethods() {
        return notificationMethods;
    }

}
