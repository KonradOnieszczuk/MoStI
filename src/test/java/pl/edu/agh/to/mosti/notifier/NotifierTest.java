/*
package pl.edu.agh.to.mosti.notifier;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.to.mosti.Application;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class NotifierTest {
    @Autowired
    Notifier notifier;

    @Test
    public void fullNotification() throws InvalidNotificationType {
        List<Pair<NotificationType, String>> notificationTypes = new ArrayList<>();
        notificationTypes.add(new Pair<>(NotificationType.email, "testaddress"));
        NotificationRequest request = new NotificationRequest(new PageChange(), notificationTypes);
        System.out.println("Notifier " + notifier);
        Section section = new Section("url", "title", "select", 2, Arrays.asList(new Notification
                (NotificationType.email, "testaddress")));
        notifier.notify(section, "nowe", "stare");
    }

    @Test
    public void emailNotificationTest(){
        Injector injector = Guice.createInjector(new TestInjector());
        PageChange pageChange = mock(PageChange.class);
        when(pageChange.getTitle()).thenReturn("Mock");
        when(pageChange.getNewValue()).thenReturn("NewPrice");
        when(pageChange.getOldValue()).thenReturn("OldPrice");

        EmailSender emailSender = injector.getInstance(EmailSender.class);

        emailSender.sendNotification(pageChange, "testaddress");

    }

    @Test
    public void requestTest() throws InvalidNotificationType {
        Injector injector = Guice.createInjector(new TestInjector());

        Notifier notifier = injector.getInstance(Notifier.class);

        NotificationRequest request = mock(NotificationRequest.class);
        PageChange pageChange = mock(PageChange.class);

        when(request.getPageChange()).thenReturn(pageChange);

        List<Pair<NotificationType, String>> notifications = new ArrayList<Pair<NotificationType, String>>();
        notifications.add(new Pair<>(NotificationType.email, ""));

        when(request.getNotificationMethods()).thenReturn(notifications);

        notifier.notify(request);
    }

    @Test
    public void senderFactoryTest() throws InvalidNotificationType {
        NotificationSenderFactory factory = new NotificationSenderFactory();
        assertEquals(factory.provideNotificationSender(NotificationType.email).getClass(), EmailSender.class);
    }
}*/
