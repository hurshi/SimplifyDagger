package io.github.hurshi.simplifydagger.processor.utils;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.tools.JavaFileObject;

public class Utils {

    public static void writeJavaFile(Filer filer, String fileName, StringBuilder content) {
        try {
            JavaFileObject file = filer.createSourceFile(fileName);
            try (Writer writer = file.openWriter()) {
                writer.write(content.toString());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> getWrappers(RoundEnvironment env, Class<? extends Annotation> c, WrapperParser<T> parser) {
        List<T> autoComponentWrappers = new LinkedList<>();
        for (Element element : env.getElementsAnnotatedWith(c)) {
            if (element.getKind() == ElementKind.CLASS) {
                for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                    if (c.getName().equals(mirror.getAnnotationType().toString())) {
                        T wrapper = parser.parse(mirror, element);
                        if (null != wrapper) {
                            autoComponentWrappers.add(wrapper);
                        }
                    }
                }
            }
        }
        return autoComponentWrappers;
    }

    public interface WrapperParser<T> {
        T parse(AnnotationMirror mirror, Element element);
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static String getSameHead(String str1, String str2) {
        if (str1.length() <= 0) return str2;
        if (str2.length() <= 0) return str1;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                stringBuilder.append(str1.charAt(i));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

}
