package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.util.List;

public interface SectionSnapshotDao {
    List<SectionSnapshot> findBySectionId(long id);
    SectionSnapshot findFirstBySectionIdOrderByDateDesc(long id);
    SectionSnapshot save(SectionSnapshot sectionSnapshot);
    void delete(SectionSnapshot sectionSnapshot);
}
