package pl.edu.agh.to.mosti.fetcher;


import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.agh.to.mosti.comparator.model.Section;
import pl.edu.agh.to.mosti.fetcher.helpers.FetchRequestMatcher;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntervalControllerTest {

    @Test
    public void intervalControllerTest() throws InterruptedException {

        long id = 1;
        long interval = 1;

        IntervalController controller = new IntervalController();

        // Checkpoint for id should not be registered
        assertFalse(controller.contains(id));

        // Set 1 checkpoint
        controller.setCheckpoint(id);

        // Checkpoint for id should be registered
        assertTrue(controller.contains(id));

        // Interval should not elapse yet
        assertFalse(controller.elapsed(id, interval));

        Thread.currentThread().sleep(1100);

        // Interval should elapse
        assertTrue(controller.elapsed(id, interval));

        // Set 2 checkpoint
        controller.setCheckpoint(id);

        // Checkpoint for id should be registered
        assertTrue(controller.contains(id));

        // Interval should not elapse yet
        assertFalse(controller.elapsed(id, interval));

        Thread.currentThread().sleep(1100);

        // Interval should elapse
        assertTrue(controller.elapsed(id, interval));
    }
}
