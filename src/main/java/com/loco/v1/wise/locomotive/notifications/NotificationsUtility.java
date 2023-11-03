package com.loco.v1.wise.locomotive.notifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class NotificationsUtility {

    NotificationConfig notificationConfig = new NotificationConfig();

    public void sendConfirmationBookingMessage(String trainId, String trainName, String trainNumber, String passengerName) {

        String messageBody = "Dear " + passengerName + ",\n\n"
                + "We are pleased to inform you that your booking for train " + trainName + " (Train Number: " + trainNumber + ") has been confirmed. Your seat on this train (Train ID: " + trainId + ") is now secured.\n\n"
                + "We look forward to welcoming you on board. If you have any further questions or require assistance, please don't hesitate to contact our customer support.\n\n"
                + "Thank you for choosing us for your travel needs.\n\n"
                + "Sincerely,\n"
                + "Your Travel Booking Team";

        notificationConfig.sendSMS(messageBody);
    }


    public void sendMoneyCreditedNotification(String accountNumber, String passengerName, double trainTicketPrice) {

        String messageBody = "Dear " + passengerName + ",\n"
                + "We've credited $" + trainTicketPrice + " to your account (" + accountNumber + ") for your train ticket booking. If you have questions, contact us.\n"
                + "Thank you for choosing us!\n"
                + "Sincerely, Your Travel Team";

        notificationConfig.sendSMS(messageBody);
    }

}
