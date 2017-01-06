package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;
import pl.edu.agh.to.mosti.notifier.Notifier;

import java.util.Date;

@Component
public final class ContentComparator implements Comparator {

    private final SectionService sectionService;
    private final SectionSnapshotService sectionSnapshotService;

    private Notifier notifier;

    @Autowired
    public ContentComparator(SectionService sectionService,
                             SectionSnapshotService sectionSnapshotService) {
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
        String previousContent = sectionSnapshot != null ? sectionSnapshot.getContent() : null;

        if (!content.equals(previousContent)) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(section, content, new Date()));

            if (notifier != null && previousContent != null) {
                // use notifier
            }
        }
    }

    @Override
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
}
