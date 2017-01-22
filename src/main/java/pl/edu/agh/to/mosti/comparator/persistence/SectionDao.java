package pl.edu.agh.to.mosti.comparator.persistence;

import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.List;

public interface SectionDao {
    List<Section> findAll();
    Section findOne(long id);
    Section save(Section section);
    void delete(Section section);
}
