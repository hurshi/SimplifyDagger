package io.github.hurshi.simplifydagger.processor;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;


public class JavaCodeGenerator {
    public static void autoComponentGenerator(Filer filer, AutoComponentWrapper wrapper) {
        String className = wrapper.getTypeElement().getSimpleName().toString();
        String packageName = wrapper.getTypeElement().getQualifiedName().toString().replace("." + className, "");
        String realClassName = "Auto" + className + "Component";

        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n")
                .append("\n")

                .append("@").append(wrapper.getScopeValue().toString()).append("\n")
                .append("@").append("dagger.Component(modules = { ").append(wrapper.getModuleValue().toString()).append(".class })\n")

                .append("public interface ").append(realClassName).append(" {\n")
                .append("   void inject(").append(wrapper.getTypeElement().getQualifiedName().toString()).append(" target);\n")
                .append("}\n");

        try {
            JavaFileObject file = filer.createSourceFile(packageName + "." + realClassName);
            try (Writer writer = file.openWriter()) {
                writer.write(builder.toString());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
