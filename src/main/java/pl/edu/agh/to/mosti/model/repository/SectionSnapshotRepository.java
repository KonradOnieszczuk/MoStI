package pl.edu.agh.to.mosti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.mosti.model.SectionSnapshot;

public interface SectionSnapshotRepository extends JpaRepository<SectionSnapshot, Long> {
    SectionSnapshot findFirstByObservedSectionIdOrderByDateDesc(long id);
}
