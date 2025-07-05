package dev.mauhux.apps.orders.security.app;

import java.util.Base64;

public class AppEncoderMain {

    public static void main(String[] args) {
        // System.out.println(new BCryptPasswordEncoder().encode("123"));

        String encodedString = Base64.getEncoder().encodeToString("Mauhux2025".getBytes());
        System.out.println(encodedString);

        byte[] encoded = Base64.getDecoder().decode("TWF1aHV4MjAyNQ==");
        System.out.println(new String(encoded));
    }
}
