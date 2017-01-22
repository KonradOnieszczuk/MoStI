package pl.edu.agh.to.mosti.comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.mosti.Application;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.notifier.NotificationType;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class SectionServiceTest {

    @Autowired
    private SectionService sectionService;

    private List<Section> testSections = new LinkedList<>();

    @Before
    public void addTestData() {
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification(NotificationType.email, "mail@mail.com"));

        for (int i = 0; i < 5; i++) {
            testSections.add(sectionService.saveOrUpdateSection(new Section(
                    "url_" + i, "alias_" + i, "selector_" + i, 0, notifications
            )));
        }
    }

    @Test
    public void sectionServiceShouldReturnProperSection() throws Exception {
        // given
        Section testSection = testSections.get(0);
        long sectionId = testSection.getId();

        // when
        Section fetchedSection = sectionService.getSectionById(sectionId);

        // then
        assertEquals(testSection, fetchedSection);
    }

    @Test
    public void sectionServiceShouldReturnNullValueWhenSectionDoesNotExist() {
        // given
        long sectionId = -1;

        // when
        Section section = sectionService.getSectionById(sectionId);

        // then
        assertNull(section);
    }

    @Test
    public void sectionServiceShouldReturnAllAvailableSections() {
        // when
        List<Section> sections = sectionService.getAllSections();

        // then
        assertEquals(testSections, sections);
    }

    @Test
    public void sectionServiceShouldDeleteSectionFromDatabase() {
        // when
        sectionService.deleteSection(testSections.get(0));
        testSections.remove(0);

        List<Section> fetchedList = sectionService.getAllSections();

        // then
        assertEquals(testSections, fetchedList);
    }

    @Test
    public void sectionServiceShouldDoNothingWhenTryingToDeleteNotExistingSection() {
        // given
        List<Section> beforeDeleting = sectionService.getAllSections();

        // when
        sectionService.deleteSection(new Section());
        List<Section> afterDeleting = sectionService.getAllSections();

        // then
        assertEquals(beforeDeleting, afterDeleting);
    }

    @Test
    public void sectionServiceShouldPointForTheSameNotificationInstance() {
        // given
        List<Section> sections = sectionService.getAllSections();

        // then
        for (int i = 0; i < sections.size() - 1; i++) {
            assertEquals(sections.get(i).getNotifications().get(0), sections.get(i + 1).getNotifications().get(0));
        }
    }

    @Test
    public void sectionServiceShouldUpdateSectionNotificationRelation() {
        // given
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification(NotificationType.email, "mail@mail.com"));

        Section firstSection = new Section("1", "1", "1", 0, notifications);
        Section secondSection = new Section("2", "2", "2", 0, notifications);

        // when
        firstSection = sectionService.saveOrUpdateSection(firstSection);
        secondSection = sectionService.saveOrUpdateSection(secondSection);

        List<Notification> firstSectionNotifications = new LinkedList<>(firstSection.getNotifications());
        List<Notification> secondSectionNotifications = new LinkedList<>(secondSection.getNotifications());

        // then
        assertEquals(1, firstSectionNotifications.size());
        assertEquals(1, secondSectionNotifications.size());
        assertEquals(firstSectionNotifications.get(0).getId(), secondSectionNotifications.get(0).getId());
    }

    @Test
    public void sectionServiceShouldReturnProperNotificationsSet() {
        // given
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification(NotificationType.email, "mail@mail.com"));
        Section section = sectionService.saveOrUpdateSection(new Section("u", "a", "s", 0, notifications));

        // when
        List<Notification> fetchedNotifications = sectionService.getNotificationsForSection(section);

        // then
        assertEquals(notifications, fetchedNotifications);
    }

    @Test
    public void sectionServiceShouldUpdateSectionNotificationRelationOnSectionDelete() {
        // given
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification(NotificationType.email, "mail@mail.com"));
        Section section = sectionService.saveOrUpdateSection(new Section("u", "a", "s", 0, notifications));

        // when
        sectionService.deleteSection(section);
    }

    @Test
    public void sectionServiceShouldPersistOnlyOneInstanceOfEqualNotifications() {
        // when
        Section firstSection = sectionService.saveOrUpdateSection(new Section("u_1", "a_1", "s_1", 0, Arrays.asList(new Notification(NotificationType.email, "mail"))));
        Section secondSection = sectionService.saveOrUpdateSection(new Section("u_2", "a_2", "s_2", 0, Arrays.asList(new Notification(NotificationType.email, "mail"))));

        // then
        assertEquals(firstSection.getNotifications().get(0).getId(), secondSection.getNotifications().get(0).getId());
    }
}



