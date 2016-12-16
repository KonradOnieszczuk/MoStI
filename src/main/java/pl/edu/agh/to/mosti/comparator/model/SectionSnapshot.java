package pl.edu.agh.to.mosti.comparator.model;

import java.util.Date;

public class SectionSnapshot {

    private long id;
    private Section section;
    private String content;
    private Date date;

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
}
