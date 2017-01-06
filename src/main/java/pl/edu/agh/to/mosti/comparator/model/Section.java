package pl.edu.agh.to.mosti.comparator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String url;
    private String alias;
    private String selector;
    private int interval;

    // For the first iteration we're supporting only EMAIL notification type.
    // This field holds e-mail address to send notifications to.
    private String contactInfo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section")
    private List<SectionSnapshot> sectionSnapshot = new LinkedList<>();

    public Section(String url, String alias, String selector,
                   String contactInfo, int interval) {
        this.url = url;
        this.alias = alias;
        this.selector = selector;
        this.contactInfo = contactInfo;
        this.interval = interval;
    }
}