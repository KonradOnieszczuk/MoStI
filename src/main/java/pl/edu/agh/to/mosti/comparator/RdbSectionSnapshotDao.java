package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.util.List;

final class RdbSectionSnapshotDao extends SectionSnapshotDao {
    @Override
    protected List<SectionSnapshot> getAllBySectionId(long id) {
        return null;
    }

    @Override
    protected SectionSnapshot getLatestBySectionId(long id) {
        return null;
    }

    @Override
    protected void save(SectionSnapshot sectionSnapshot) {

    }

    @Override
    protected void update(SectionSnapshot sectionSnapshot) {

    }

    @Override
    protected void delete(SectionSnapshot sectionSnapshot) {

    }
}
