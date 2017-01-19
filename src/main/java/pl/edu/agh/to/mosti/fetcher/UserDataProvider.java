package pl.edu.agh.to.mosti.fetcher;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.notifier.Notifier;
import pl.edu.agh.to.mosti.notifier.NotifierInjector;

@Component
@Transactional
public class UserDataProvider {

    private SectionService sectionService;
    private Comparator comparator;

    private IntervalController controller = new IntervalController();
    private Decider decider = new Decider();

    @Autowired
    public UserDataProvider(SectionService sectionService, Comparator comparator) {
        this.sectionService = sectionService;
        this.comparator = comparator;

        this.decider = new Decider();
        this.decider.register( new StaticFetcher() );
        this.decider.register( new DynamicFetcher() );
    }

    @Scheduled(fixedRate=200)
    public void ProcessFetch(){

        List <Section> sections = sectionService.getAllSections();

        for ( final Section section : sections ) {

            if ( !controller.contains(section.getId()) || controller.elapsed( section.getId(), section.getInterval() ))
            {
                FetchRequest request = new FetchRequest();
                request.setURL( section.getUrl() );
                request.setSelector( section.getSelector() );
                request.setInterval( section.getInterval() );

                try {

                    FetchResult result = decider.decide( request );

                    comparator.compare( section.getId(), result.getText() );

                    controller.setCheckpoint( section.getId() );

                } catch (FetcherException e) {
                    System.out.println("Decider exception: " + e.getMessage() );
                }
            }
        }
    }
}
