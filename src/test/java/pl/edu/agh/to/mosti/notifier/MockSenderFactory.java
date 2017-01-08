package pl.edu.agh.to.mosti.notifier;

import static org.mockito.Mockito.mock;


public class MockSenderFactory implements SenderFactory {
    @Override
    public NotificationSender provideNotificationSender(NotificationType type) throws InvalidNotificationType {
        EmailSender sender = mock(EmailSender.class);
        return sender;
    }
}
