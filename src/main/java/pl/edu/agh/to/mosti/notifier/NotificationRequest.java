package pl.edu.agh.to.mosti.notifier;

import javafx.util.Pair;

import java.util.List;

public class NotificationRequest {
    private PageChange pageChange;
    private List<Pair<NotificationType, String>> notificationMethods;   //String is address or identifier used to send

    public PageChange getPageChange() {
        return pageChange;
    }

    public void setPageChange(PageChange pageChange) {
        this.pageChange = pageChange;
    }

    public List<Pair<NotificationType, String>> getNotificationMethods() {
        return notificationMethods;
    }

    public void setNotificationMethods(List<Pair<NotificationType, String>> notificationMethods) {
        this.notificationMethods = notificationMethods;
    }
}
