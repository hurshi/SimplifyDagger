package io.github.hurshi.simplifydagger.processor;

public class Logger {
    public static void log(String message) {
        System.out.println(">>> " + message);
    }

    public static void log(String... message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : message) {
            stringBuilder.append("  ").append(s).append("\n");
        }
        System.out.println(">>>\n" + stringBuilder.toString());
    }
}
