package com.loco.v1.wise.locomotive.payloads;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyPayloads {

    private static int counter = 0;
    private static final String prefix = "B";
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String TICKET_PREFIX = "GSY";
    private static final String SEAT_PREFIX = "T";
    private static int ticketNumberCounter = 1;
    private static int seatNumberCounter = 1;
    private static final String TICKET_SUFFIX = "GHU";

    // Train INIT
    public static String forTrainInIt() {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuilder add = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            add.append(randomChar);
        }

        return add.toString();
    }

    // Train NUMBER
    public static String forTrainNumber() {

        String digits = "0123456789";
        StringBuilder idBuilder = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(digits.length());
            char randomChar = digits.charAt(randomIndex);
            idBuilder.append(randomChar);
        }

        return idBuilder.toString();
    }

    public static String generateTicketNumber() {
        String formattedNumber = String.format("%s%03d%s", TICKET_PREFIX, ticketNumberCounter, TICKET_SUFFIX);
        ticketNumberCounter++;
        return formattedNumber;
    }

    // Generate a new seat number
    public static String generateSeatNumber() {
        String formattedNumber = String.format("%s%d", SEAT_PREFIX, seatNumberCounter);
        seatNumberCounter++;
        return formattedNumber;
    }


    public static String forPnrNumberGenerator() {

        String digits = "0123456789";
        StringBuilder idBuilder = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(digits.length());
            char randomChar = digits.charAt(randomIndex);
            idBuilder.append(randomChar);
        }

        return idBuilder.toString();
    }

    public static String generateBogieName() {

        counter++;
        StringBuilder randomChars = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            randomChars.append(characters.charAt(random.nextInt(characters.length())));
        }

        return prefix + counter + randomChars.toString();
    }

}

