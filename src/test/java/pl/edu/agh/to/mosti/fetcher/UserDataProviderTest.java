package pl.edu.agh.to.mosti.fetcher;


import org.junit.Test;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.fetcher.helpers.FetchRequestMatcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class UserDataProviderTest {

    @Test
    public void userDataProviderSuccessTest() throws FetchException {

        // Test: Check that comparator is notified when page is fetched correctly

        // --- Sections provider Mock
        Section section = new Section("url_0", "alias_0", "selector_0", 0, new LinkedList<>() );

        List<Section> sections = new ArrayList<>();
        sections.add( section );

        SectionService sectionsService = Mockito.mock(SectionService.class);
        Mockito.when(sectionsService.getAllSections()).thenReturn(sections);

        // --- Comparator Mock
        Comparator comparator = Mockito.mock(Comparator.class);

        // --- Prepare request and response
        FetchRequest request = new FetchRequest();
        request.setURL( section.getUrl() );
        request.setSelector( section.getSelector() );
        request.setInterval( section.getInterval() );

        FetchResult response = new FetchResult();
        response.setText("text");

        // --- Decider Mock
        Decider decider =  Mockito.mock(Decider.class);
        Mockito.when(decider.decide(Mockito.argThat(new FetchRequestMatcher( request )))).thenReturn(response);

        // --- Provider call
        UserDataProvider provider = new UserDataProvider( sectionsService, comparator );
        provider.setDecider( decider );
        provider.process();

        // --- Verify if comparator is called
        Mockito.verify(comparator, Mockito.times(1)).compare(section.getId(), response.getText());
    }

    @Test
    public void userDataProviderFailureTest() throws FetchException {

        // Test: Check if comparator is not notified when page is not fetched

        // --- Sections provider Mock
        Section section = new Section("url_0", "alias_0", "selector_0", 0, new LinkedList<>() );

        List<Section> sections = new ArrayList<>();
        sections.add( section );

        SectionService sectionsService = Mockito.mock(SectionService.class);
        Mockito.when(sectionsService.getAllSections()).thenReturn(sections);

        // --- Comparator Mock
        Comparator comparator = Mockito.mock(Comparator.class);

        // --- Prepare request and response
        FetchRequest request = new FetchRequest();
        request.setURL( section.getUrl() );
        request.setSelector( section.getSelector() );
        request.setInterval( section.getInterval() );

        FetchResult response = new FetchResult();
        response.setText("text");

        // --- Decider Mock
        Decider decider = Mockito.mock(Decider.class);
        Mockito.when(decider.decide(Mockito.argThat(new FetchRequestMatcher( request )))).thenThrow(new FetchException("test ex"));

        // --- Provider call
        UserDataProvider provider = new UserDataProvider( sectionsService, comparator );
        provider.setDecider( decider );
        provider.process();

        // --- Verify if comparator is not called
        Mockito.verify(comparator, Mockito.never()).compare(section.getId(), "text");
    }
}
