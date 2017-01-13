package pl.edu.agh.to.mosti.comparator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = { "sections" })
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "notifications")
    private List<Section> sections;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private String contactInfo;

    public Notification(NotificationType notificationType, String contactInfo) {
        this.notificationType = notificationType;
        this.contactInfo = contactInfo;
    }
}
