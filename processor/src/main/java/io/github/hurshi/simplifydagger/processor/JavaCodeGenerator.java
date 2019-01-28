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

        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n")
                .append("\n");

        //add scope if exist
        if (null != wrapper.getScopeValue() && wrapper.getScopeValue().toString().length() > 0) {
            builder.append("@").append(wrapper.getScopeValue().toString()).append("\n");
        }

        builder.append("@").append("dagger.Component(");

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

        builder.append("public interface ").append(realClassName).append(" {\n")
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
