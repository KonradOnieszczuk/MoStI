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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;
    private String alias;
    private String selector;
    private int interval;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section", cascade = CascadeType.REMOVE)
    private List<SectionSnapshot> sectionSnapshot = new LinkedList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "section_notification",
            joinColumns = @JoinColumn(name = "section_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id"))
    private List<Notification> notifications;

    public Section(String url, String alias, String selector,int interval, List<Notification> notifications) {
        this.url = url;
        this.alias = alias;
        this.selector = selector;
        this.interval = interval;
        this.notifications = notifications;
    }

}