package io.github.hurshi.simplifydagger.processor.utils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Logger {
    private static Messager messager;

    public static void init(Messager m) {
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

    public static void printStackTrace(Exception e) {
        StringBuilder stringBuilder = new StringBuilder(e.getMessage()).append("\n").append(e.getCause().toString()).append("\n");
        for (int i = 0; i < e.getStackTrace().length; i++) {
            stringBuilder.append(e.getStackTrace()[i].toString()).append("\n");
        }
        Logger.log("!!!!!!!!!!!! error !!!!!!!!!!!!\n" + stringBuilder.toString());
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
