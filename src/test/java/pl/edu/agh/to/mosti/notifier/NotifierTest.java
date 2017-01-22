package pl.edu.agh.to.mosti.notifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.to.mosti.Application;
import pl.edu.agh.to.mosti.comparator.model.Notification;
import pl.edu.agh.to.mosti.comparator.model.Section;

import javax.mail.*;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class NotifierTest {
    @Autowired
    private Notifier notifier;

    @Autowired
    private NotificationSenderFactory senderFactory;

    @Autowired
    private EmailSender emailSender;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    /** Tests if email notification sender uses the send method and the correct address */
    public void emailSenderTest() throws MessagingException {
        String testAddress = "testAddress";
        EmailTransportFactory transportFactory = mock(EmailTransportFactory.class);

        Transport mockTransport = mock(Transport.class);
        when(transportFactory.getTransport()).thenReturn(mockTransport);

        // Session is final and cannot be mocked, so mock has to return a real instance.
        when(transportFactory.getSession()).thenReturn(Session.getInstance(new Properties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("", "");
                    }
                }));

        emailSender.setTransportFactory(transportFactory);
        emailSender.sendNotification(mock(PageChange.class), testAddress);

        verify(mockTransport).connect();

        ArgumentCaptor<Address[]> addressCaptor = ArgumentCaptor.forClass(Address[].class);
        verify(mockTransport).sendMessage(isA(Message.class), addressCaptor.capture());
        Address[] addresses = addressCaptor.getValue();
        assertEquals(addresses.length, 1);
        assertEquals(addresses[0].toString(), testAddress);
    }

    @Test
    /** Tests if Notifier uses the correct senders */
    public void notifierTest() {
        Section section = mock(Section.class);
        Notification notification = mock(Notification.class);
        String testAddress = "testAddress";

        when(notification.getNotificationType()).thenReturn(NotificationType.email);
        when(notification.getContactInfo()).thenReturn(testAddress);
        when(section.getNotifications()).thenReturn(Arrays.asList(notification));

        NotificationSenderFactory factory = mock(NotificationSenderFactory.class);
        EmailSender emailSender = mock(EmailSender.class);
        when(factory.provideNotificationSender(NotificationType.email)).thenReturn(emailSender);
        notifier.setSenderFactory(factory);

        notifier.notify(section, "current", "previous");

        verify(factory).provideNotificationSender(NotificationType.email);
        verifyNoMoreInteractions(factory);
        verify(emailSender).sendNotification(any(PageChange.class), eq(testAddress));
    }

    @Test
    /** Tests if the Sender Factory returns the correct sender class for requested notification type */
    public void senderFactoryTest() {
        EmailSender email = mock(EmailSender.class);
        SMSSender sms = mock(SMSSender.class);

        when(email.getSupportedType()).thenReturn(NotificationType.email);
        when(sms.getSupportedType()).thenReturn(NotificationType.sms);

        senderFactory.setNotificationSenders(Arrays.asList(email, sms));

        assertEquals(senderFactory.provideNotificationSender(NotificationType.email), email);
        assertEquals(senderFactory.provideNotificationSender(NotificationType.sms), sms);
    }

    @Test
    /** Tests if notificationSenderFactory throws exception when it doesn't have the requested sender */
    public void noSuchSender() {
        expectedException.expect(InvalidNotificationType.class);

        EmailSender email = mock(EmailSender.class);
        when(email.getSupportedType()).thenReturn(NotificationType.email);
        senderFactory.setNotificationSenders(Arrays.asList(email));

        senderFactory.provideNotificationSender(NotificationType.sms);
    }
}