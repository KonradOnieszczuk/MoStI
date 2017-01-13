package pl.edu.agh.to.mosti.comparator;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.mosti.comparator.model.Notification;

public interface RdbNotificationDao extends NotificationDao,
        JpaRepository<Notification, Long> {
}
