package pl.edu.agh.to.mosti.comparator;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.mosti.comparator.model.Section;

interface RdbSectionDao extends SectionDao, JpaRepository<Section, Long> {
}
