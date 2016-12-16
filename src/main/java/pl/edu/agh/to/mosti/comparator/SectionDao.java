package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

abstract class SectionDao {
    protected abstract List<Section> getAll();
    protected abstract Section getById(long id);
    protected abstract void save(Section section);
    protected abstract void update(Section section);
    protected abstract void delete(Section section);
}
