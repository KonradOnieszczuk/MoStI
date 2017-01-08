package pl.edu.agh.to.mosti.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

@Service
public final class SectionService {
    private final SectionDao sectionDao;

    @Autowired
    public SectionService(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    public Section getSectionById(long id) {
        return sectionDao.getOne(id);
    }

    public List<Section> getAllSections() {
        return sectionDao.findAll();
    }

    public Section saveOrUpdateSection(Section section) {
        return sectionDao.save(section);
    }

    public void deleteSection(Section section) {
        sectionDao.delete(section);
    }

}
