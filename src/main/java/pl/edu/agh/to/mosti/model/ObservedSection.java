package pl.edu.agh.to.mosti.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ObservedSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // default constructor just to allow JPA instantiation
    public ObservedSection() {}

    public ObservedSection(String sectionName) {
        this.sectionName = sectionName;
    }

    private String sectionName;

    @OneToMany(mappedBy = "observedSection")
    private List<SectionSnapshot> sectionSnapshots;

    // presumably Notification methods for Notifier module
    // ...

    public long getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }
}
