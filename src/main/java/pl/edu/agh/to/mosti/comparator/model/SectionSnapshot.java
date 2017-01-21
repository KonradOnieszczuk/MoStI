package pl.edu.agh.to.mosti.comparator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SectionSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    private Section section;

    public SectionSnapshot(Section section, String content) {
        this.section = section;
        this.content = content;
        this.date = new Date();
    }
}