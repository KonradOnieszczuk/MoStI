package pl.edu.agh.to.mosti.notifier;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class NotificationSenderFactory implements SenderFactory {

    public NotificationSender provideNotificationSender(NotificationType type) throws InvalidNotificationType {
        Injector injector = Guice.createInjector(new NotifierInjector());

        if( type.equals(NotificationType.email) ) {
            return injector.getInstance(EmailSender.class);
        } else if (type.equals(NotificationType.hangout)){
            return new HangoutsSender();
        }
        // return statement needed - throw exception instead of return null
        throw new InvalidNotificationType(type);
    }
}
