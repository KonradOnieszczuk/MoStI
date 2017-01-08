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

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class SectionServiceTest {

    @Autowired
    private SectionService sectionService;

    private List<Section> testSections = new LinkedList<>();

    @Before
    public void addTestData() {
        for (int i = 0; i < 5; i++) {
            testSections.add(sectionService.saveOrUpdateSection(new Section(
                    "url_" + i, "alias_" + i, "selector_" + i, "contact_" + i, 0
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

    @Test(expected = EntityNotFoundException.class)
    public void sectionServiceShouldThrowEntityNotFoundExceptionWhenSectionDoesNotExist() {
        // given
        long sectionId = -1;

        // when
        sectionService.getSectionById(sectionId).getId();
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
}



