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

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class ContentComparatorTest {

    @Autowired
    private Comparator Comparator;

    @Autowired
    private SectionSnapshotService sectionSnapshotService;

    @Autowired
    private SectionService sectionService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void contentComparatorShouldSaveLatestSnapshotOnNoticedDifference() throws NoSuchSectionException {

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
        List<SectionSnapshot> sectionSnapshotList = sectionSnapshotService.findBySectionId(section.getId());

        Comparator.compare(section.getId(), "different");

        List<SectionSnapshot> sectionSnapshotListMirror = sectionSnapshotService.findBySectionId(section.getId());

        // then
        assertNotEquals(sectionSnapshotList, sectionSnapshotListMirror);
        assertEquals("different", sectionSnapshotListMirror.get(sectionSnapshotListMirror.size()-1).getContent());
    }
}
