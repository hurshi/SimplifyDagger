package io.github.hurshi.simplifydagger.processor;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;


class JavaCodeGenerator {
    static void autoComponentGenerator(Filer filer, AutoComponentWrapper wrapper) {
        String className = wrapper.getTypeElement().getSimpleName().toString();
        String packageName = wrapper.getTypeElement().getQualifiedName().toString().replace("." + className, "");
        String realClassName = "Auto" + className + "Component";

        StringBuilder builder = new StringBuilder()
                .append(appendPackage(packageName))
                .append(appendScope(wrapper))
                .append(appendComponent(wrapper))
                .append(appendBody(wrapper, realClassName));

        writeJavaFile(filer, packageName + "." + realClassName, builder);
    }


    private static StringBuilder appendPackage(String packageName) {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n")
                .append("\n");
        return builder;
    }

    private static StringBuilder appendScope(AutoComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        //add scope if exist
        if (null != wrapper.getScopeValue() && wrapper.getScopeValue().toString().length() > 0) {
            builder.append("@").append(wrapper.getScopeValue().toString()).append("\n");
        }
        return builder;
    }

    private static StringBuilder appendComponent(AutoComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder("@dagger.Component(");

        //add modules if exist
        boolean modulesIsNotEmpty = false;
        if (null != wrapper.getModulesValue() && wrapper.getModulesValue().toString().length() > 0) {
            modulesIsNotEmpty = true;
            builder.append(" modules = { ").append(wrapper.getModulesValue().toString()).append(" }");
        }

        //add dependencies if exist
        if (null != wrapper.getDependenciesValue() && wrapper.getDependenciesValue().toString().length() > 0) {
            if (modulesIsNotEmpty) builder.append(",");
            builder.append(" dependencies = { ").append(wrapper.getDependenciesValue().toString()).append(" }");
        }

        builder.append(")\n");
        return builder;
    }


    private static StringBuilder appendBody(AutoComponentWrapper wrapper, String className) {
        StringBuilder builder = new StringBuilder();
        builder.append("public interface ").append(className).append(" {\n")
                .append("   void inject(").append(wrapper.getTypeElement().getQualifiedName().toString()).append(" target);\n")
                .append("}\n");
        return builder;
    }

    private static void writeJavaFile(Filer filer, String fileName, StringBuilder content) {
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
}
