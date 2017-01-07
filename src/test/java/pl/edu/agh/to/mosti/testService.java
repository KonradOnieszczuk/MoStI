package pl.edu.agh.to.mosti;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import pl.edu.agh.to.mosti.comparator.SectionDao;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.comparator.SectionSnapshotDao;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class testService {

    @Mock
    private SectionDao mock;
    @Mock
    private SectionSnapshotDao mock1;

    private SectionService sectionService;

    @Before
    public void setUp() {
        sectionService = new SectionService(mock);
    }

    @Test
    public void testService() throws Exception {

        Mockito.when(sectionService.getSectionById(anyLong())).thenAnswer(new Answer<Section>()
        {
            @Override
            public Section answer(InvocationOnMock invocation) throws Throwable {
                return new Section();
            }
        });

        Section section = new Section();
        assertEquals(section, sectionService.getSectionById(0));
    }
    @Test
    public void testService1() throws Exception {

        Mockito.when(sectionService.getAllSections()).thenAnswer(new Answer<List<Section>>()
        {
            @Override
            public List<Section> answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<Section>();
            }
        });

        List<Section> section = new ArrayList<Section>();
        assertEquals(section, sectionService.getAllSections());
    }
    @Test
    public void testService2() throws Exception {

        Mockito.when(mock.save(anyObject())).thenAnswer(new Answer<Section>()
        {
            @Override
            public Section answer(InvocationOnMock invocation) throws Throwable {
                return new Section("wp.pl", "wp", "aktualnosci", "janusz32@gmail.com", 5);
            }
        });
        Mockito.when(sectionService.getSectionById(anyLong())).thenAnswer(new Answer<Section>()
        {
            @Override
            public Section answer(InvocationOnMock invocation) throws Throwable {
                return new Section("wp.pl", "wp", "aktualnosci", "janusz32@gmail.com", 5);
            }
        });

        Section section = new Section("wp.pl", "wp", "aktualnosci", "janusz32@gmail.com", 5);
        assertEquals(section, mock.save(section));
        assertEquals(section, sectionService.getSectionById(0));
    }

    @Test
    public void testService3() throws Exception {

        Section section = new Section("wp.pl", "wp", "aktualnosci", "janusz32@gmail.com", 5);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = "07.01.2017";
        Date date = dateFormat.parse(dateString);



        Mockito.when(mock1.save(anyObject())).thenAnswer(new Answer<SectionSnapshot>()
        {
            @Override
            public SectionSnapshot answer(InvocationOnMock invocation) throws Throwable {
                return new SectionSnapshot(section, "zawartosc", date);
            }
        });

        SectionSnapshot sectionSnapshot = new SectionSnapshot(section, "zawartosc", date);
        assertEquals(sectionSnapshot, mock1.save(sectionSnapshot));

    }

    @Test
    public void testService4() throws Exception {

        Section section = new Section("wp.pl", "wp", "aktualnosci", "janusz32@gmail.com", 5);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = "07.01.2017";
        Date date = dateFormat.parse(dateString);

        Mockito.when(mock1.findFirstBySectionIdOrderByDateDesc(anyLong())).thenAnswer(new Answer<SectionSnapshot>()
        {
            @Override
            public SectionSnapshot answer(InvocationOnMock invocation) throws Throwable {
                return new SectionSnapshot(section, "zawartosc", date);
            }
        });

        SectionSnapshot sectionSnapshot = new SectionSnapshot(section, "zawartosc", date);
        assertEquals(sectionSnapshot, mock1.findFirstBySectionIdOrderByDateDesc(0));

    }
}



