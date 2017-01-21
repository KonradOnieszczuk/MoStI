package pl.edu.agh.to.mosti.comparator.model;

import lombok.*;
import pl.edu.agh.to.mosti.notifier.NotificationType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = { "sections" })
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "notifications")
    private List<Section> sections;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private String contactInfo;

    public Notification() {
        this.notificationType = null;
        this.contactInfo = null;
    }

    public Notification(NotificationType notificationType, String contactInfo) {
        this.notificationType = notificationType;
        this.contactInfo = contactInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
