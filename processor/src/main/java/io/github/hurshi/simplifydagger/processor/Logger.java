package io.github.hurshi.simplifydagger.processor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Logger {
    private static Messager messager;

    static void init(Messager m) {
        messager = m;
    }

    public static void log(String message) {
        checkMessageNotNull();
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, ">>> " + message);
    }

    public static void log(String... message) {
        checkMessageNotNull();
        StringBuilder stringBuilder = new StringBuilder(">>>\n");
        for (String s : message) {
            stringBuilder.append("  ").append(s).append("\n");
        }
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, stringBuilder.toString());
    }

    private static void checkMessageNotNull() {
        if (null == messager) {
            throw new IllegalStateException("messager 没有初始化");
        }
    }


//    public static void log(String message) {
//        System.out.println(">>> " + message);
//    }
//
//    public static void log(String... message) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : message) {
//            stringBuilder.append("  ").append(s).append("\n");
//        }
//        System.out.println(">>>\n" + stringBuilder.toString());
//    }
}
