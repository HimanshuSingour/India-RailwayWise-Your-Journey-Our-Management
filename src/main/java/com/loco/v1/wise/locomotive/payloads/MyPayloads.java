package com.loco.v1.wise.locomotive.payloads;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyPayloads {

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

}
