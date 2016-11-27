package pl.edu.agh.to.mosti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.model.ObservedSection;
import pl.edu.agh.to.mosti.model.SectionSnapshot;
import pl.edu.agh.to.mosti.model.repository.ObservedSectionRepository;
import pl.edu.agh.to.mosti.model.repository.SectionSnapshotRepository;

import java.util.Date;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ObservedSectionRepository observedRepository, SectionSnapshotRepository snapshotRepository, Comparator comparator) {
        return (args) -> {
            // creating mock section snapshot
            ObservedSection observedSection = new ObservedSection("test");
            observedSection = observedRepository.save(observedSection);
            observedRepository.flush();

            SectionSnapshot sectionSnapshot = new SectionSnapshot(observedSection, "currentContent", new Date());
            sectionSnapshot = snapshotRepository.save(sectionSnapshot);
            snapshotRepository.flush();

            comparator.checkSnapshot(observedSection.getId(), "newContent");
        };
    }

}
