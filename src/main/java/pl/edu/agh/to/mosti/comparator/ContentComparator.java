package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;
import pl.edu.agh.to.mosti.notifier.*;

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
        Section section = sectionService.getSectionById(id);

        if (section == null) {
            throw new NoSuchSectionException();
        }

        String oldContent = sectionSnapshotService.getLatestSectionSnapshotContent(section);

        if (!newContent.equals(oldContent)) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(section, newContent));

            if (notifier != null && oldContent != null) {
               notifier.notify(section, newContent, oldContent);
            }
        }
    }

    @Override
    @Autowired
    public void setNotifier(INotifier notifier) {
        this.notifier = notifier;
    }
}
