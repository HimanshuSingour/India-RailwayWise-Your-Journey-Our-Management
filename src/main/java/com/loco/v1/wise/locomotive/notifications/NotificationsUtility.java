package com.loco.v1.wise.locomotive.notifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class NotificationsUtility {

    NotificationConfig notificationConfig = new NotificationConfig();

    public void sendConfirmationBookingMessage(String trainId, String trainName, String trainNumber, String passengerName) {

        String messageBody = "Dear " + passengerName + ",\n"
                + "Your booking for " + trainName + " (Train #" + trainNumber + ") is confirmed. Your seat (ID: " + trainId + ") is reserved. We look forward to welcoming you on board. For assistance, contact us. Thank you for choosing us!\n"
                + "Sincerely, Your Travel Team";

        notificationConfig.sendSMS(messageBody);
    }


    public void sendMoneyCreditedNotification(String accountNumber, String passengerName, double trainTicketPrice) {

        String messageBody = "Dear " + passengerName + ",\n"
                + "We've credited $" + trainTicketPrice + " to your account (" + accountNumber + ") for your train ticket booking. If you have questions, contact us.\n"
                + "Thank you for choosing us!\n"
                + "Sincerely, Your Travel Team";

        notificationConfig.sendSMS(messageBody);
    }

    public void sendBookingCancellationNotification(String trainName, String trainNumber, String ticketNumber, String passengerName) {

        String messageBody = "Dear " + passengerName + ",\n"
                + "Your booking for " + trainName + " (Ticket #" + ticketNumber + ") has been canceled. We will process your refund within 2 minutes, and you should expect the funds back in your account within 2-3 business days.\n"
                + "For any further assistance or inquiries, please feel free to contact us.\n"
                + "Thank you for choosing us!\n"
                + "Sincerely, Your Travel Team";

        notificationConfig.sendSMS(messageBody);
    }

    public void sendRefundWithPercentageDeductionNotification(String accountNumber, double trainTicketPrice) {

        double deductionPercentage = 10;
        double deductedAmount = (deductionPercentage / 100) * trainTicketPrice;
        double refundedAmount = trainTicketPrice - deductedAmount;

        String messageBody = "Refund Notice for Account " + accountNumber + ":\n"
                + "We have processed a refund of $" + refundedAmount + " to your account after deducting " + deductionPercentage + "% due to cancellation. The refunded amount should be available in your account shortly.\n"
                + "If you have any questions or need further information, please don't hesitate to reach out to our customer support.\n"
                + "Thank you for your understanding.\n"
                + "Sincerely, Your Travel Team";

        notificationConfig.sendSMS(messageBody);
    }


}
