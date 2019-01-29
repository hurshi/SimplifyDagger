package io.github.hurshi.simplifydagger.processor.auto_android_component.auto_component;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.processing.Filer;

import io.github.hurshi.simplifydagger.processor.utils.Constant;
import io.github.hurshi.simplifydagger.processor.utils.Utils;


class AutoAndroidComponentJavaCodeGenerator {
    static void autoComponentGenerator(Filer filer, List<AutoAndroidComponentWrapper> wrappers) {
        if (wrappers.size() <= 0) {
            return;
        }
        String className = "AutoAndroidComponentInjector";
        String packageName = "io.github.hurshi.simplifydagger";
        StringBuilder builder = new StringBuilder()
                .append(appendPackage(packageName))
                .append("@dagger.Module\n")
                .append("public abstract class ").append(className).append(" {\n\n")
                .append(appendBody(wrappers))
                .append("}");

        Utils.writeJavaFile(filer, packageName + "." + className, builder);
    }


    private static StringBuilder appendPackage(String packageName) {
        StringBuilder builder = new StringBuilder("// Generated by SimplifyDagger (https://github.com/hurshi/SimplifyDagger).\n");
        builder.append("package ").append(packageName).append(";\n")
                .append("\n");
        return builder;
    }

    private static StringBuilder appendBody(List<AutoAndroidComponentWrapper> wrappers) {
        StringBuilder builder = new StringBuilder();
        wrappers.forEach(wrapper -> {
            builder.append(appendSingleWrapper(wrapper));
        });
        return builder;
    }

    private static StringBuilder appendSingleWrapper(AutoAndroidComponentWrapper wrapper) {
        return new StringBuilder()
                .append(appendScope(wrapper))
                .append(appendInjectorAndModules(wrapper))
                .append(appendMethod(wrapper));
    }


    private static StringBuilder appendScope(AutoAndroidComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        //add scope if exist
        if (null != wrapper.getScopeValue() && wrapper.getScopeValue().toString().length() > 0
                && wrapper.getScopeValue() != void.class) {
            builder.append(Constant.TAB).append("@").append(wrapper.getScopeValue().toString()).append("\n");
        }
        return builder;
    }

    private static StringBuilder appendInjectorAndModules(AutoAndroidComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder(Constant.TAB).append("@dagger.android.ContributesAndroidInjector(");

        //add modules if exist
        if (null != wrapper.getModulesValue() && wrapper.getModulesValue().toString().length() > 0) {
            builder.append("modules = {").append(wrapper.getModulesValue().toString()).append("}");
        }

        builder.append(")\n");
        return builder;
    }

    private static StringBuilder appendMethod(AutoAndroidComponentWrapper wrapper) {
        return new StringBuilder(Constant.TAB)
                .append("public abstract ").append(wrapper.getTypeElement().getQualifiedName().toString())
                .append(" inject").append(wrapper.getTypeElement().getSimpleName().toString())
                .append("();\n\n");
    }


}
