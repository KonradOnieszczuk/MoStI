package pl.edu.agh.to.mosti.comparator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.mosti.Application;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class ComparatorTest {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionSnapshotService sectionSnapshotService;

    @Autowired
    private Comparator comparator;

    private List<Section> testSections = new LinkedList<>();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void addTestData() {
        for (int i = 0; i < 5; i++) {
            Section section = new Section("url_" + i, "alias_" + i, "selector_" + i, "contact_" + i, 0);

            List<SectionSnapshot> sectionSnapshots = new LinkedList<>();

            for (int j = 0; j < 3 && i != 4; j++) {
                sectionSnapshots.add(new SectionSnapshot(section, "content_" + i + "_" + j, new Date(2016, 0, j)));
            }

            section.setSectionSnapshot(sectionSnapshots);
            testSections.add(sectionService.saveOrUpdateSection(section));
        }
    }

    @Test
    public void comparatorShouldDetectAndPersistChanges() {
        // given
        String newContent = "content_0_4";
        Section section = testSections.get(0);

        // when
        comparator.compare(section.getId(), newContent);

        // then
        assertTrue(sectionSnapshotService.getLatestSectionSnapshot(section).getContent().equals(newContent));
    }

    @Test
    public void comparatorShouldNotPersistChangesWhenThereIsNoChange() {
        // given
        String newContent = "content_0_2";
        Section section = testSections.get(0);

        // when
        comparator.compare(section.getId(), newContent);

        // then
        assertEquals(section.getSectionSnapshot(), sectionService.getSectionById(section.getId()).getSectionSnapshot());
    }


    @Test(expected = NoSuchSectionException.class)
    public void comparatorShouldThrowNoSuchSectionExceptionWhenSectionOfGivenIdDoesNotExist() {
        // given
        long sectionId = -1;

        // when
        comparator.compare(sectionId, "");

        // then
        // expect exception
    }

    @Test
    public void comparatorShouldPersistNewSnapshotWhenNoSnapshotsAreConnectedToGivenSection() {
        // given
        Section section = testSections.get(testSections.size() - 1);

        // when
        comparator.compare(section.getId(), "content");

        // then
        assertTrue(sectionSnapshotService.getLatestSectionSnapshot(section).getContent().equals("content"));
    }
}
