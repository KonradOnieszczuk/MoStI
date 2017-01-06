package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

interface SectionDao {
    List<Section> findAll();
    Section getOne(long id);
    Section save(Section section);
    void delete(Section section);
}
