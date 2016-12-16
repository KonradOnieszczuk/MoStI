package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.util.List;

abstract class SectionSnapshotDao {
    protected abstract List<SectionSnapshot> getAllBySectionId(long id);
    protected abstract SectionSnapshot getLatestBySectionId(long id);
    protected abstract void save(SectionSnapshot sectionSnapshot);
    protected abstract void update(SectionSnapshot sectionSnapshot);
    protected abstract void delete(SectionSnapshot sectionSnapshot);
}
