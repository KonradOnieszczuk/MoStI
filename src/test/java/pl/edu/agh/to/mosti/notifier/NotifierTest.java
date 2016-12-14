package pl.edu.agh.to.mosti.notifier;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotifierTest {
    @Test
    public void emailNotificationTest(){

        final String testAddress = "";        //insert address before test

        NotificationSenderFactory factory = new NotificationSenderFactory();
        try {
            NotificationSender sender = factory.provideNotificationSender(NotificationType.email);
            sender.sendNotification("hello world", testAddress);
        } catch (InvalidNotificationType invalidNotificationType) {
            invalidNotificationType.printStackTrace();
            fail();
        }
    }

}