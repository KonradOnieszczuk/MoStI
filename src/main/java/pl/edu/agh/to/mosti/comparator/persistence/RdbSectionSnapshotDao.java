package pl.edu.agh.to.mosti.comparator.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

interface RdbSectionSnapshotDao extends SectionSnapshotDao,
        JpaRepository<SectionSnapshot, Long> {
}