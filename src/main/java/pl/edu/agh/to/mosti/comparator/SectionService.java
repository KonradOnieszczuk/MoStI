package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.LinkedList;
import java.util.List;

@Service
public final class SectionService {
    private final SectionDao sectionDao;
    private final NotificationDao notificationDao;

    @Autowired
    public SectionService(SectionDao sectionDao, NotificationDao notificationDao) {
        this.sectionDao = sectionDao;
        this.notificationDao = notificationDao;
    }

    public Section getSectionById(long id) {
        return sectionDao.getOne(id);
    }

    public List<Section> getAllSections() {
        return sectionDao.findAll();
    }

    public List<Notification> getNotificationsForSection(Section section) {
        return notificationDao.findBySections_Id(section.getId());
    }

    public Section saveOrUpdateSection(Section section) {
        List<Notification> notifications = section.getNotifications() != null ? section.getNotifications() : new LinkedList<>();

        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notificationDao.findFirstByNotificationTypeAndContactInfo(notifications.get(i).getNotificationType(), notifications.get(i).getContactInfo());
            if (notification != null) {
                notifications.set(i, notification);
            }
        }

        return sectionDao.save(section);
    }

    public void deleteSection(Section section) {
        sectionDao.delete(section);
    }

}
