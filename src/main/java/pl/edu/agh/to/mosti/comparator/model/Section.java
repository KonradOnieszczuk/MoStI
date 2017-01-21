package pl.edu.agh.to.mosti.comparator.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
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

    public Section() {
        this.url = null;
        this.alias = null;
        this.selector = null;
        this.interval = 0;
        this.notifications = null;
    }

    public Section(String url, String alias, String selector,int interval, List<Notification> notifications) {
        this.url = url;
        this.alias = alias;
        this.selector = selector;
        this.interval = interval;
        this.notifications = notifications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<SectionSnapshot> getSectionSnapshot() {
        return sectionSnapshot;
    }

    public void setSectionSnapshot(List<SectionSnapshot> sectionSnapshot) {
        this.sectionSnapshot = sectionSnapshot;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
