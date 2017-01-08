package pl.edu.agh.to.mosti.notifier;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotifierTest {
    @Test
    public void emailNotificationTest(){

        final String testAddress = "ggorska.a@gmail.com";        //insert address before test

        NotificationSenderFactory factory = new NotificationSenderFactory();
        try {
            NotificationSender sender = factory.provideNotificationSender(NotificationType.email);
            PageChange pg = new PageChange();
            sender.sendNotification(pg, testAddress);
        } catch (InvalidNotificationType invalidNotificationType) {
            invalidNotificationType.printStackTrace();
            fail();
        }
    }

    @Test
    public void senderFactoryTest() {
        NotificationSenderFactory factory = new NotificationSenderFactory();

        //assert(factory.provideNotificationSender(NotificationType.email)
    }
/* Mail session */
}