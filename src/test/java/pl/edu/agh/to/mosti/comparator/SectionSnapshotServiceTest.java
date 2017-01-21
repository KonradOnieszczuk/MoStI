package pl.edu.agh.to.mosti.comparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.mosti.Application;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class SectionSnapshotServiceTest {

    class SectionSnapshotDateComparator implements Comparator<SectionSnapshot> {
        @Override
        public int compare(SectionSnapshot firstSnapshot, SectionSnapshot secondSnapshot) {
            return secondSnapshot.getDate().compareTo(firstSnapshot.getDate());
        }
    }

    @Autowired
    private SectionSnapshotService sectionSnapshotService;

    @Autowired
    private SectionService sectionService;

    @Test
    public void sectionSnapshotServiceShouldReturnLatestSectionSnapshot() {

        // given
        Section section = sectionService.saveOrUpdateSection(new Section(
                "url", "alias", "selector", 0, null
        ));

        for (int i = 0; i < 5; i++) {
            sectionSnapshotService.saveSnapshot(new SectionSnapshot(
                    section, "content" + i
            ));
        }

        // when
        SectionSnapshot sectionSnapshot = sectionSnapshotService.getLatestSectionSnapshot(section);
        List<SectionSnapshot> sectionSnapshotList = sectionSnapshotService.findBySectionId(section.getId());

        sectionSnapshotList.sort(new SectionSnapshotDateComparator());

        // then
        assertEquals(sectionSnapshot, sectionSnapshotList.get(0));
    }

    @Test
    public void sectionSnapshotServiceShouldReturnProperSectionSnapshot() throws Exception {
        // given
        List<SectionSnapshot> testSectionSnapshots = new LinkedList<>();
        Section section = sectionService.saveOrUpdateSection(new Section(
                "url_", "alias_", "selector_", 0, null
        ));

        for (int i = 0; i < 5; i++) {
            testSectionSnapshots.add(sectionSnapshotService.saveSnapshot(new SectionSnapshot(section, "content" + i)));
        }

        // when
        List <SectionSnapshot> testSectionSnapshotsMirror = sectionSnapshotService.findBySectionId(section.getId());

        // then
        assertEquals(testSectionSnapshots, testSectionSnapshotsMirror);
    }
}
