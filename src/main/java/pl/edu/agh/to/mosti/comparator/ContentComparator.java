package pl.edu.agh.to.mosti.comparator;

import javafx.util.Pair;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;
import pl.edu.agh.to.mosti.notifier.NotificationType;
import pl.edu.agh.to.mosti.notifier.Notifier;
import pl.edu.agh.to.mosti.notifier.PageChange;
import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class ContentComparator implements Comparator {

    private final SectionService sectionService;
    private final SectionSnapshotService sectionSnapshotService;

    private Notifier notifier;

    public ContentComparator(SectionService sectionService, SectionSnapshotService sectionSnapshotService) {
        this.sectionService = sectionService;
        this.sectionSnapshotService = sectionSnapshotService;
    }

    @Override
    public void compare(long id, String content) throws NoSuchSectionException {
        Section section = sectionService.getSectionById(id);

        if (section == null) {
            throw new NoSuchSectionException();
        }

        SectionSnapshot sectionSnapshot = sectionSnapshotService.getLatestSectionSnapshot(section);

        if (sectionSnapshot == null) {
            throw new IllegalStateException("At least one snapshot should be associated " +
                    "with every section. Section id: " + section.getId());
        }

        if (!content.equals(sectionSnapshot.getContent())) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(section, content, new Date()));

            if (notifier != null) {
                notifier.Notify(buildPageChange(section, sectionSnapshot.getContent(), content));
            }
        }
    }

    @Override
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    // In the next iteration we're going to build a SectionNotification model object, so it
    // will be more suitable to use that instead of a PageChange object.
    private PageChange buildPageChange(Section section, String oldContent, String newContent) {
        List<Pair<NotificationType, String>> notificationMethods = new LinkedList<>();
        notificationMethods.add(new Pair<>(NotificationType.email, section.getContactInfo()));

        PageChange pageChange = new PageChange();
        pageChange.setTitle(section.getAlias());
        pageChange.setOldValue(oldContent);
        pageChange.setNewValue(newContent);
        pageChange.setUrl(section.getPageUrl());
        pageChange.setNotificationMethods(notificationMethods);

        return pageChange;
    }
}
