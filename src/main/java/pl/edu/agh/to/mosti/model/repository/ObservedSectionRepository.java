package pl.edu.agh.to.mosti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.to.mosti.model.ObservedSection;

public interface ObservedSectionRepository extends JpaRepository<ObservedSection, Long> {

}
