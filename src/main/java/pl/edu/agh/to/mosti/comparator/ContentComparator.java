package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.mosti.model.SectionSnapshot;
import pl.edu.agh.to.mosti.model.repository.SectionSnapshotRepository;
import pl.edu.agh.to.mosti.notifier.Notifier;

@Service
public class ContentComparator implements Comparator {

    private SectionSnapshotRepository repository;

    @Autowired
    public ContentComparator(SectionSnapshotRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNotifier(Notifier notifier) {
        // TODO
    }

    @Override
    public void checkSnapshot(long observedPageSectionId, String currentContent) {
        SectionSnapshot snapshot = repository.findFirstByObservedSectionIdOrderByDateDesc(observedPageSectionId);

        if (snapshot.getContent().equals(currentContent)) {
            System.out.println("THERE WAS NO CHANGE");
        } else {
            System.out.println("CHANGE DETECTED");
        }
    }
}
