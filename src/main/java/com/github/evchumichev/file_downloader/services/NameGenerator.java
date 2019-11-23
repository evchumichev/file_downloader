package com.github.evchumichev.file_downloader.services;

import java.util.Random;

public class NameGenerator {
    private final String stringOfChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private final char dot = '.';
    private static NameGenerator nameGenerator;
    private Random random;

    public static synchronized NameGenerator getInstance() {
        if (nameGenerator == null) {
            nameGenerator = new NameGenerator();
        }
        return nameGenerator;
    }

    private NameGenerator() {
        random = new Random();
    }

    public String generate(String contentType) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(stringOfChars.charAt(random.nextInt(stringOfChars.length() - 1)));
        }
        builder.append(dot);
        builder.append(contentType.split("/")[1]);
        return builder.toString();
    }
}
