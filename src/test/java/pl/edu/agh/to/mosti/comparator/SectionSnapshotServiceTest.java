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
import java.util.Comparator;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class SectionSnapshotServiceTest {

    class sectionSnapshotComparator implements Comparator<SectionSnapshot> {
        public int compare(SectionSnapshot sectionSnapshot1, SectionSnapshot sectionSnapshot2) {
            return sectionSnapshot2.getDate().compareTo(sectionSnapshot1.getDate());
        }
    }

    @Autowired
    private SectionSnapshotService sectionSnapshotService;

    @Autowired
    private SectionService sectionService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void sectionSnapshotServiceShouldReturnLatestSectionSnapshot() {

        //before
        Section section = sectionService.saveOrUpdateSection(new Section(
                "url_", "alias_", "selector_", "contact_", 0
        ));
        for (int i = 0; i < 5; i++) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(
                    section, "content" + i, new Date()
            ));
        }

        // when
        SectionSnapshot sectionSnapshot = sectionSnapshotService.getLatestSectionSnapshot(section);

        List<SectionSnapshot> sectionSnapshotList = sectionSnapshotService.findBySectionId(section.getId());

        sectionSnapshotList.sort(new sectionSnapshotComparator());

        SectionSnapshot sectionSnapshotMirror = sectionSnapshotList.get(0);

        // then
        assertEquals(sectionSnapshot, sectionSnapshotMirror);
    }

    @Test
    public void sectionSnapshotServiceShouldReturnProperSectionSnapshot() throws Exception {

        //given
        List<SectionSnapshot> testSectionSnapshots = new LinkedList<>();
        Section section = sectionService.saveOrUpdateSection(new Section(
                "url_", "alias_", "selector_", "contact_", 0
        ));
        for (int i = 0; i < 5; i++) {
            testSectionSnapshots.add(new SectionSnapshot(section, "content" + i, new Date()));
            sectionSnapshotService.saveSnapshot(testSectionSnapshots.get(i));


        }

        // when
        List <SectionSnapshot> testSectionSnapshotMirror = sectionSnapshotService.findBySectionId(section.getId());


        // then
        assertEquals(testSectionSnapshots, testSectionSnapshotMirror);
    }
}
