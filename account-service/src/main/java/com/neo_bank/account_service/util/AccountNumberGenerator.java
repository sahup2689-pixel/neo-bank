package com.neo_bank.account_service.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random random = new Random();

    public static String generateAccountNumber() {

        return String.valueOf(
                1000000000L +
                        (long)(random.nextDouble() * 9000000000L)
        );
    }
}