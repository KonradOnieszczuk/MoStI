package pl.edu.agh.to.mosti.comparator.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.mosti.comparator.model.Section;

interface RdbSectionDao extends SectionDao, JpaRepository<Section, Long> {
}
