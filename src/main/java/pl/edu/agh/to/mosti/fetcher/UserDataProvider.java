package pl.edu.agh.to.mosti.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.mosti.comparator.Comparator;
import pl.edu.agh.to.mosti.comparator.SectionService;
import pl.edu.agh.to.mosti.comparator.model.Section;

@Component
@Transactional
public class UserDataProvider {

    private SectionService service;
    private Comparator comparator;
    private Decider decider;

    private IntervalController controller = new IntervalController();

    public void setDecider(Decider decider) {
        this.decider = decider;
    }

    @Autowired
    public UserDataProvider(SectionService service, Comparator comparator) {
        this.service = service;
        this.comparator = comparator;
    }

    @Scheduled(fixedRate=2500)
    public void process(){

        List <Section> sections = service.getAllSections();

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

                } catch (FetchException e) {
                    System.out.println("Decider exception: " + e.getMessage() );
                }
            }
        }
    }
}
