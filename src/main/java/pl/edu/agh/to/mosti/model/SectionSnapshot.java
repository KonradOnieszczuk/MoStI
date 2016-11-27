package pl.edu.agh.to.mosti.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SectionSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public SectionSnapshot() {

    }

    public SectionSnapshot(ObservedSection observedSection, String content, Date date) {
        this.observedSection = observedSection;
        this.content = content;
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "observedSectionId")
    private ObservedSection observedSection;

    private String content;

    private Date date;

    public long getId() {
        return id;
    }

    public ObservedSection getObservedSection() {
        return observedSection;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
