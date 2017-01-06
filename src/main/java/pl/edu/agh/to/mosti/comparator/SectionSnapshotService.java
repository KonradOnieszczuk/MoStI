package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

@Service
class SectionSnapshotService {
    private final SectionSnapshotDao sectionSnapshotDao;

    @Autowired
    SectionSnapshotService(SectionSnapshotDao sectionSnapshotDao) {
        this.sectionSnapshotDao = sectionSnapshotDao;
    }

    SectionSnapshot getLatestSectionSnapshot(Section section) {
        return sectionSnapshotDao.findFirstBySectionIdOrderByDateDesc(section.getId());
    }

    void saveSnapshot(SectionSnapshot sectionSnapshot) {
        sectionSnapshotDao.save(sectionSnapshot);
    }
}
