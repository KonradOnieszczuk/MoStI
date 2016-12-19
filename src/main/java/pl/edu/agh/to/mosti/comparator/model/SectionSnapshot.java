package pl.edu.agh.to.mosti.comparator.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class SectionSnapshot {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String content;
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECTION_ID")
    private Section section;

    public SectionSnapshot() {

    }

    public SectionSnapshot(Section section, String content, Date date) {
        this.section = section;
        this.content = content;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    @Override
    public String toString() {
        return String.format(
                "Section[id=%d, section='%s', content='%s', date='%s']",
                id, section, content, date);
    }
}
