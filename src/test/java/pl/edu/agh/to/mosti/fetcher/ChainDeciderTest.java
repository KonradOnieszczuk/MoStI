package pl.edu.agh.to.mosti.fetcher;


import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.agh.to.mosti.comparator.model.Section;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ChainDeciderTest {

    @Test
    public void chainDeciderStaticPageTest() throws FetchException {

        // Test: Verify that when static fetcher succeeded, dynamic fetcher is not chosen.

        // --- Section

        Section section = new Section("url_", "alias_", "selector_", 0, new LinkedList<>() );

        // --- Decider Mock

        FetchRequest request = new FetchRequest();
        request.setURL( section.getUrl() );
        request.setSelector( section.getSelector() );
        request.setInterval( section.getInterval() );

        FetchResult response = new FetchResult();
        response.setText("text");

        // Simulate static fetcher
        Fetcher fStatic = Mockito.mock(Fetcher.class);
        Mockito.when(fStatic.fetch(request)).thenReturn(response);

        // Simulate dynamic fetcher
        Fetcher fDynamic = Mockito.mock(Fetcher.class);

        Decider decider = new ChainDecider();
        decider.register( fStatic );
        decider.register( fDynamic );

        // Verify return value
        assertEquals( decider.decide( request ), response ) ;

        // Verify that fetch was called only once on every fetcher
        Mockito.verify(fStatic, Mockito.times(1)).fetch(request);
        Mockito.verify(fDynamic, Mockito.never()).fetch(request);
    }

    @Test
    public void chainDeciderDynamicPageTest() throws FetchException {

        // Test: Verify that when static fetcher throws, dynamic fetcher is chosen.

        // --- Section

        Section section = new Section("url_", "alias_", "selector_", 0, new LinkedList<>() );

        // --- Decider Mock

        FetchRequest request = new FetchRequest();
        request.setURL( section.getUrl() );
        request.setSelector( section.getSelector() );
        request.setInterval( section.getInterval() );

        FetchResult response = new FetchResult();
        response.setText("text");

        // Simulate static fetcher
        Fetcher fStatic = Mockito.mock(Fetcher.class);
        Mockito.when(fStatic.fetch(request)).thenThrow(new FetchException("test exception"));

        // Simulate dynamic fetcher
        Fetcher fDynamic = Mockito.mock(Fetcher.class);
        Mockito.when(fDynamic.fetch(request)).thenReturn(response);

        Decider decider = new ChainDecider();
        decider.register( fStatic );
        decider.register( fDynamic );

        // Verify return value
        assertEquals( decider.decide( request ), response ) ;

        // Verify that fetch was called only once on every fetcher
        Mockito.verify(fStatic, Mockito.times(1)).fetch(request);
        Mockito.verify(fDynamic, Mockito.times(1)).fetch(request);
    }

    @Test(expected=FetchException.class)
    public void chainDeciderFailureTest() throws FetchException {

        // Test: Verify that when static fetcher throws, dynamic fetcher is chosen, and when it throws decider also throws.

        // --- Section

        Section section = new Section("url_", "alias_", "selector_", 0, new LinkedList<>() );

        // --- Decider Mock

        FetchRequest request = new FetchRequest();
        request.setURL( section.getUrl() );
        request.setSelector( section.getSelector() );
        request.setInterval( section.getInterval() );

        FetchResult response = new FetchResult();
        response.setText("text");

        // Simulate static fetcher
        Fetcher fStatic = Mockito.mock(Fetcher.class);
        Mockito.when(fStatic.fetch(request)).thenThrow(new FetchException("test exception"));

        // Simulate dynamic fetcher
        Fetcher fDynamic = Mockito.mock(Fetcher.class);
        Mockito.when(fDynamic.fetch(request)).thenThrow(new FetchException("test exception"));

        Decider decider = new ChainDecider();
        decider.register( fStatic );
        decider.register( fDynamic );

        // Verify return value
        assertEquals( decider.decide( request ), response ) ;

        // Verify that fetch was called only once on every fetcher
        Mockito.verify(fStatic, Mockito.times(1)).fetch(request);
        Mockito.verify(fDynamic, Mockito.times(1)).fetch(request);
    }
}
