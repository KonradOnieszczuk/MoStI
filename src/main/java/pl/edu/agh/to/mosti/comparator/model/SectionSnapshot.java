package pl.edu.agh.to.mosti.comparator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SectionSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    private Section section;

    public SectionSnapshot() {
        this.section = null;

        this.content = null;
        this.date = null;
    }

    public SectionSnapshot(Section section, String content) {
        this.section = section;
        this.content = content;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
