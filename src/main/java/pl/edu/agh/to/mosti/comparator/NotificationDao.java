package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.notifier.NotificationType;

import java.util.List;

public interface NotificationDao {
    Notification findFirstByNotificationTypeAndContactInfo(NotificationType notificationType, String contactInfo);
    List<Notification> findBySections_Id(long id);
    Notification save(Notification notification);
    void delete(Notification notification);
}
