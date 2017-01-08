package pl.edu.agh.to.mosti.notifier;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.util.Pair;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotifierTest {
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
}