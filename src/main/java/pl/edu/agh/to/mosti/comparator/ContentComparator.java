package pl.edu.agh.to.mosti.comparator;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;
import pl.edu.agh.to.mosti.notifier.*;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

@Component
public final class ContentComparator implements Comparator {

    private final SectionService sectionService;
    private final SectionSnapshotService sectionSnapshotService;

    private INotifier notifier;

    @Autowired
    public ContentComparator(SectionService sectionService,
                             SectionSnapshotService sectionSnapshotService) {
        this.sectionService = sectionService;
        this.sectionSnapshotService = sectionSnapshotService;
    }

    @Override
    public void compare(long id, String newContent) {
        Section section;

        try {
            section = sectionService.getSectionById(id);
            section.getId();
        } catch (EntityNotFoundException ex) {
            throw new NoSuchSectionException();
        }

        SectionSnapshot sectionSnapshot = sectionSnapshotService.getLatestSectionSnapshot(section);
        String oldContent = sectionSnapshot != null ? sectionSnapshot.getContent() : null;

        if (!newContent.equals(oldContent)) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(section, newContent));

            if (notifier != null && oldContent != null) {
                try {
                    notifier.notify(buildNotificationRequest(section, oldContent, newContent));
                } catch (InvalidNotificationType invalidNotificationType) {
                    invalidNotificationType.printStackTrace();
                }
            }
        }
    }

    private NotificationRequest buildNotificationRequest(Section section, String oldContent, String newContent) {
        PageChange pageChange = new PageChange();
        pageChange.setUrl(section.getUrl());
        pageChange.setTitle(section.getAlias());
        pageChange.setOldValue(oldContent);
        pageChange.setNewValue(newContent);

        List<Pair<NotificationType, String>> notifications = new LinkedList<>();
        for (Notification notification : section.getNotifications()) {
            notifications.add(new Pair<>(notification.getNotificationType(), notification.getContactInfo()));
        }

        return new NotificationRequest(pageChange, notifications);
    }

    @Override
    @Autowired
    public void setNotifier(INotifier notifier) {
        this.notifier = notifier;
    }
}
