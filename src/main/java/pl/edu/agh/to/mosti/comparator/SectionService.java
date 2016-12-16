package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

public final class SectionService {

    private final SectionDao sectionDao;
    private final SectionSnapshotDao sectionSnapshotDao;

    public SectionService(SectionDao sectionDao, SectionSnapshotDao sectionSnapshotDao) {
        this.sectionDao = sectionDao;
        this.sectionSnapshotDao = sectionSnapshotDao;
    }

    public Section getSectionById(long id) {
        return sectionDao.getById(id);
    }

    public List<Section> getAllSections() {
        return sectionDao.getAll();
    }

    public void saveOrUpdateSection(Section section) {
        if (sectionDao.getById(section.getId()) == null) {
            sectionDao.save(section);
        } else {
            sectionDao.update(section);
        }
    }

    public void deleteSection(Section section) {
        sectionDao.delete(section);
    }
}
